#!/usr/bin/env bash

/usr/local/bin/docker run \
  -v $PWD/datasources:/usr/local/datasources \
  -v $PWD/logs:/usr/local/logs \
  -e TZ=Asia/Tokyo \
  --rm shinsei-bank-t-point-campaign-assign:latest