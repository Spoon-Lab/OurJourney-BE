# Generated by Django 4.2 on 2024-09-14 11:39

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="Comment",
            fields=[
                ("id", models.AutoField(primary_key=True, serialize=False)),
                ("content", models.CharField(max_length=500)),
                ("created_at", models.DateTimeField(auto_now_add=True)),
                ("updated_at", models.DateTimeField(auto_now=True)),
                ("deleted_at", models.DateTimeField(default=None, null=True)),
            ],
            options={
                "db_table": "comment",
                "managed": True,
            },
        ),
        migrations.CreateModel(
            name="ContentLike",
            fields=[
                ("id", models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                "db_table": "content_like",
                "managed": True,
            },
        ),
        migrations.CreateModel(
            name="Contents",
            fields=[
                ("id", models.AutoField(primary_key=True, serialize=False)),
                ("title", models.CharField(max_length=255)),
                ("content_img", models.CharField(max_length=200, null=True)),
                ("created_at", models.DateTimeField(auto_now_add=True)),
                ("updated_at", models.DateTimeField(default=None, null=True)),
                ("deleted_at", models.DateTimeField(default=None, null=True)),
            ],
            options={
                "db_table": "contents",
                "managed": True,
            },
        ),
        migrations.CreateModel(
            name="ContentTag",
            fields=[
                ("id", models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                "db_table": "content_tag",
                "managed": True,
            },
        ),
        migrations.CreateModel(
            name="Tag",
            fields=[
                ("id", models.AutoField(primary_key=True, serialize=False)),
                ("name", models.CharField(max_length=10)),
                ("deleted_at", models.DateTimeField(default=None, null=True)),
            ],
            options={
                "db_table": "tag",
                "managed": True,
            },
        ),
        migrations.CreateModel(
            name="Thread",
            fields=[
                ("id", models.AutoField(primary_key=True, serialize=False)),
                ("content", models.CharField(max_length=500)),
                ("thread_img", models.CharField(max_length=200, null=True)),
                ("created_at", models.DateTimeField(auto_now_add=True)),
                ("updated_at", models.DateTimeField(default=None, null=True)),
                ("deleted_at", models.DateTimeField(default=None, null=True)),
                (
                    "content_id",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        to="contentapp.contents",
                    ),
                ),
            ],
            options={
                "db_table": "thread",
                "managed": True,
            },
        ),
        migrations.CreateModel(
            name="ThreadTag",
            fields=[
                ("id", models.AutoField(primary_key=True, serialize=False)),
                (
                    "tag_id",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE, to="contentapp.tag"
                    ),
                ),
                (
                    "thread_id",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.CASCADE,
                        to="contentapp.thread",
                    ),
                ),
            ],
            options={
                "db_table": "thread_tag",
                "managed": True,
            },
        ),
    ]
