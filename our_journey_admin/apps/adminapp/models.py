from django.db import models


# Create your models here.
class Category(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=50, null=False, default="")

    class Meta:
        managed = True
        db_table = "category"
