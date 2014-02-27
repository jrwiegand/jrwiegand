from django.template import RequestContext
from django.shortcuts import render_to_response
from .models import Category, Page

def encode_url(str):
    return str.replace(' ', '_')

def decode_url(str):
    return str.replace('_', ' ')

def index(request):
    context = RequestContext(request)

    category_list = Category.objects.order_by('-likes')[:5]

    context_dict = {'categories': category_list}

    for category in category_list:
        category.url = encode_url(category.name)

    page_views = Page.objects.order_by('-views')[:5]
    context_dict['pages'] = page_views

    return render_to_response('rango/index.html', context_dict, context)


def about(request):
    context = RequestContext(request)

    context_dict = {'bold_message': "I am bold font from the context."}

    return render_to_response('rango/about.html', context_dict, context)


def category(request, category_name_url):
    context = RequestContext(request)

    category_name = decode_url(category_name_url)

    context_dict = {'category_name': category_name}

    try:
        category = Category.objects.get(name=category_name)

        pages = Page.objects.filter(category=category).order_by('-views')

        context_dict['pages'] = pages

        context_dict['category'] = category

    except Category.DoesNotExist:
        pass

    return render_to_response('rango/category.html', context_dict, context)
