from django.conf.urls import patterns, url
from . import views

urlpatterns = patterns('',
    url(r'^$', views.index, name='index'),
    url(r'^about/$', views.about, name='about'),
    url(r'^add-category/$', views.add_category, name='add_category'),
    url(r'^category/(?P<category_name_url>[^/]+)/add-page/$', views.add_page, name='add_page'),
    url(r'^category/(?P<category_name_url>[^/]+)/$', views.category, name='category'),
    url(r'^register/$', views.register, name='register'),
    url(r'^login/$', views.user_login, name='user_login'),
    url(r'^restricted/', views.restricted, name='restricted'),
    url(r'^logout/$', views.user_logout, name='user_logout'),
    url(r'^profile/$', views.profile, name='profile'),
    url(r'^goto/$', views.track_url, name='track_url'),
    url(r'^like_category/$', views.like_category, name='like_category'),
    url(r'^suggest_category/$', views.suggest_category, name='suggest_category'),
    url(r'^auto_add_page/$', views.auto_add_page, name='auto_add_page'),
)
