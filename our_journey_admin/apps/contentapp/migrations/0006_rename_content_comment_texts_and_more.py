# Generated by Django 4.2 on 2024-09-24 13:22

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ("contentapp", "0005_rename_thread_img_thread_img_url_and_more"),
    ]

    operations = [
        migrations.RenameField(
            model_name="comment",
            old_name="content",
            new_name="texts",
        ),
        migrations.RenameField(
            model_name="thread",
            old_name="content",
            new_name="texts",
        ),
    ]
