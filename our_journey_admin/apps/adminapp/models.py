from django.db import models


# Create your models here.
class Category(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=50, null=False, default="")
    # 카테고리 버튼의 이미지 url
    img_url = models.CharField(max_length=200, null=True)
    created_at = models.DateTimeField(auto_now_add=True)

    class Meta:
        managed = True
        db_table = "category"
