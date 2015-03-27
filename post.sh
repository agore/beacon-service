#!/bin/sh

curl -H "Content-Type: application/json" --data @b.json http://localhost:8080/beacon/event
