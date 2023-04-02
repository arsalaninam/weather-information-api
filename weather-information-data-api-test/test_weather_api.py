import base64
import jsonschema
import pytest
import requests


@pytest.fixture
def base_url():
    return "http://localhost:8080"

@pytest.fixture
def auth_headers():
    username = "admin"
    password = "admin"
    headers = {
        "Authorization": "Basic " + base64.b64encode((username + ":" + password).encode("ascii")).decode("ascii")
    }
    return headers



