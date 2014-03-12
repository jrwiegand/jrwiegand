from django.http import HttpResponseRedirect, HttpResponse
from django.template import RequestContext
from django.shortcuts import render_to_response, redirect
from django.contrib.auth import logout
from django.contrib.auth import authenticate, login
from django.contrib.auth.models import User
from django.contrib.auth.decorators import login_required

from datetime import datetime

from .models import Category, Page
from .forms import CategoryForm, PageForm, UserForm, UserProfileForm
from .search import run_query


def encode_url(str):
    return str.replace(' ', '-')


def decode_url(str):
    return str.replace('-', ' ')


def index(request):
    context = RequestContext(request)

    top_category_list = Category.objects.order_by('-likes')[:5]

    for category in top_category_list:
        category.url = encode_url(category.name)

    context_dict = {'categories': top_category_list}

    cat_list = get_category_list()
    context_dict['cat_list'] = cat_list

    page_list = Page.objects.order_by('-views')[:5]
    context_dict['pages'] = page_list

    if request.session.get('last_visit'):
        last_visit_time = request.session.get('last_visit')

        visits = request.session.get('visits', 0)

        if (datetime.now() - datetime.strptime(last_visit_time[:-7], "%Y-%m-%d %H:%M:%S")).days > 0:
            request.session['visits'] = visits + 1
    else:
        request.session['last_visit'] = str(datetime.now())
        request.session['visits'] = 1

    return render_to_response('rango/index.html', context_dict, context)


def about(request):
    context = RequestContext(request)

    context_dict = {'bold_message': "I am bold font from the context."}

    count = request.session.get('visits', 0)

    context_dict['visit_count'] = count

    return render_to_response('rango/about.html', context_dict, context)


def category(request, category_name_url):
    context = RequestContext(request)

    category_name = decode_url(category_name_url)

    context_dict = {'category_name': category_name, 'category_name_url': category_name_url}

    cat_list = get_category_list()
    context_dict['cat_list'] = cat_list

    try:
        category = Category.objects.get(name__iexact=category_name)
        context_dict['category'] =category

        pages = Page.objects.filter(category=category).order_by('-views')

        context_dict['pages'] = pages

        context_dict['category'] = category

    except Category.DoesNotExist:
        pass

    if request.method == 'POST':
        query = request.POST['query'].strip()
        if query:
            result_list = run_query(query)
            context_dict ['result_list'] = result_list

    return render_to_response('rango/category.html', context_dict, context)


def add_category(request):
    context = RequestContext(request)

    if request.method == 'POST':
        form = CategoryForm(request.POST)

        if form.is_valid():
            form.save(commit=True)

            return index(request)

        else:
            print(form.errors)

    else:
        form = CategoryForm()

    return render_to_response('rango/add_category.html', {'form': form}, context)


def add_page(request, category_name_url):
    context = RequestContext(request)

    category_name = decode_url(category_name_url)

    if request.method == 'POST':
        form = PageForm(request.POST)

        if form.is_valid():
            page = form.save(commit=False)

            try:
                cat = Category.objects.get(name=category_name)
                page.category = cat
            except Category.DoesNotExist:
                return render_to_response('rango/add_category.html', {}, context)

            page.views = 0
            page.save()

            return category(request, category_name_url)
        else:
            print(form.errors)
    else:
        form = PageForm()

    return render_to_response('rango/add_page.html',
                             {'category_name_url': category_name_url,
                              'category_name'    : category_name,
                              'form'             : form}, context)


def register(request):
    context = RequestContext(request)

    registered = False

    if request.method == 'POST':
        user_form = UserForm(data=request.POST)
        profile_form = UserProfileForm(data=request.POST)

        if user_form.is_valid() and profile_form.is_valid():
            user = user_form.save()

            user.set_password(user.password)
            user.save()

            profile = profile_form.save(commit=False)
            profile.user = user

            if 'picture' in request.FILES:
                profile.picture = request.FILES['picture']

            profile.save()

            registered = True

        else:
            print(user_form.errors, profile_form.errors)

    else:
        user_form = UserForm()
        profile_form = UserProfileForm()

    return render_to_response('rango/register.html',
                             {'user_form': user_form,
                              'profile_form': profile_form,
                              'registered': registered}, context)


def user_login(request):
    context = RequestContext(request)

    if request.method == 'POST':
        username = request.POST['username']
        password = request.POST['password']

        user = authenticate(username=username, password=password)

        if user is not None:
            if user.is_active:
                login(request, user)
                return HttpResponseRedirect('/')
            else:
                return HttpResponse("Your Rango account is disabled.")
        else:
            print "Invalid login details: {0}, {1}".format(username, password)
            return HttpResponse("Invalid login details supplied.")

    else:
        return render_to_response('rango/login.html', {}, context)


@login_required
def restricted(request):
    return HttpResponse("Since you're logged in, you can see this text!")


@login_required
def user_logout(request):
    logout(request)

    return HttpResponseRedirect('/')


def search(request):
    context = RequestContext(request)
    result_list = []

    if request.method == 'POST':
        query = request.POST['query'].strip()

        if query:
            result_list = run_query(query)

    return render_to_response('rango/search.html', {'result_list': result_list}, context)

def get_category_list():
    cat_list = Category.objects.all()

    for cat in cat_list:
        cat.url = encode_url(cat.name)

    return cat_list


@login_required
def profile(request):
    context = RequestContext(request)
    cat_list = get_category_list()
    context_dict = {'cat_list':cat_list}
    current_user = User.objects.get(username=request.user)

    try:
        user_profile = UserProfile.objects.get(user=current_user)
    except:
        user_profile = None

    context_dict['user'] = current_user
    context_dict['userprofile'] = user_profile
    return render_to_response('rango/profile.html', context_dict, context)


def track_url(request):
    context = RequestContext(request)
    page_id = None
    url = '/'
    if request.method == 'GET':
        if 'page_id' in request.GET:
            page_id = request.GET['page_id']
            try:
                page = Page.objects.get(url__iexact = page_id)
                page.views = page.views + 1
                page.save()
                url = page.url
            except ValueError, e:
                print e

    return redirect(url)
