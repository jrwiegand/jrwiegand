from django.conf.urls import patterns, url
from .views import index, about, category

urlpatterns = patterns('',
    url(r'^$', index, name='index'),
    url(r'^about/$', about, name='about'),
    url(r'^category/(?P<category_name_url>\w+)/$', category, name='category'),
)
