from django.conf.urls import patterns, url
from .views import index, about, category, add_category, add_page

urlpatterns = patterns('',
    url(r'^$', index, name='index'),
    url(r'^about/$', about, name='about'),
    url(r'^add-category/$', add_category, name='add_category'),
    url(r'^category/(?P<category_name_url>[^/]+)/add-page/$', add_page, name='add_page'),
    url(r'^category/(?P<category_name_url>[^/]+)/$', category, name='category'),
)
