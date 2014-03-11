from django.conf.urls import patterns, url
from .views import index, about, category, add_category, add_page, register, user_login, restricted, user_logout, search

urlpatterns = patterns('',
    url(r'^$', index, name='index'),
    url(r'^about/$', about, name='about'),
    url(r'^add-category/$', add_category, name='add_category'),
    url(r'^category/(?P<category_name_url>[^/]+)/add-page/$', add_page, name='add_page'),
    url(r'^category/(?P<category_name_url>[^/]+)/$', category, name='category'),
    url(r'^register/$', register, name='register'),
    url(r'^login/$', user_login, name='user_login'),
    url(r'^restricted/', restricted, name='restricted'),
    url(r'^logout/$', user_logout, name='user_logout'),
    url(r'^search/$', search, name='search'),
)
