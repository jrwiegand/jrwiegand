from django.conf.urls import patterns, url
from .views import index, about

urlpatterns = patterns('',
        url(r'^$', index, name='index'),
        url(r'^about/', about, name='about'),
)
