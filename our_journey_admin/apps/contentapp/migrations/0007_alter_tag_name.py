# Generated by Django 4.2 on 2024-10-02 13:53

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ("contentapp", "0006_rename_content_comment_texts_and_more"),
    ]

    operations = [
        migrations.AlterField(
            model_name="tag",
            name="name",
            field=models.CharField(max_length=10, unique=True),
        ),
    ]
