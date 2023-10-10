#!/bin/bash

docker run -d -t -i -p 8080:8080 \
    --restart always --name telegram telegram:1.0
