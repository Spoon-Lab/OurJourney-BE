from django.contrib import admin

from apps.adminapp.models import Category


# Category 모델을 Django Admin에 등록
@admin.register(Category)
class CategoryAdmin(admin.ModelAdmin):
    list_display = ("id", "name")
