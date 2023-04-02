import base64
import jsonschema
import pytest


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

def test_get_weather_data_with_admin_credentials(base_url, auth_headers):
    response = requests.get(f"{base_url}/api/weather?city=karachi", headers=auth_headers)

    assert response.status_code == 200

def test_add_weather_data_with_admin_credentials(base_url, auth_headers):
    payload = {
        "city": "lahore",
        "date": "2023-04-02",
        "temperature": "35",
        "humidity": "60"
    }

    response = requests.post(f"{base_url}/api/weather", json=payload, headers=auth_headers)

    assert response.status_code == 200

def test_get_error_status_code(base_url, auth_headers):
    response = requests.get(f"{base_url}/api/weather/error", headers=auth_headers)

    assert response.status_code == 500

def test_add_weather_data_content(base_url, auth_headers):
    schema = {
        "type": "object",
        "properties": {
            "city": {"type": "string"},
            "date": {"type": "string", "format": "date"},
            "temperature": {"type": "string", "pattern": "^\\d+$"},
            "humidity": {"type": "string", "pattern": "^\\d+$"}
        },
        "required": ["city", "date", "temperature", "humidity"]
    }

    payload = {
        "city": "lahore",
        "date": "2023-04-02",
        "temperature": "35",
        "humidity": "60"
    }

    try:
        jsonschema.validate(payload, schema)
    except jsonschema.exceptions.ValidationError as e:
        pytest.fail(f"Schema validation failed: {e}")

    response = requests.post(f"{base_url}/api/weather", json=payload, headers=auth_headers)

    assert response.status_code == 200


def test_add_weather_data_with_invalid_credentials(base_url):
    payload = {
        "city": "lahore",
        "date": "2023-04-02",
        "temperature": "35",
        "humidity": "60"
    }

    auth_headers = {"Authorization": "Bearer invalid_token"}
    response = requests.post(f"{base_url}/api/weather", json=payload, headers=auth_headers)

    assert response.status_code == 401

import requests

def test_response_timeout(base_url):
    payload = {
        "city": "lahore",
        "date": "2023-04-02",
        "temperature": "35",
        "humidity": "60"
    }

    timeout = 0.000000001

    with pytest.raises(requests.exceptions.Timeout):
        requests.post(f"{base_url}/api/weather", json=payload, timeout=timeout)

def test_headers_validation(base_url):
    payload = {
        "city": "lahore",
        "date": "2023-04-02",
        "temperature": "35",
        "humidity": "60"
    }

    headers = {
        "Content-Type": "application/json",
        "Authorization": "Bearer token"
    }

    response = requests.post(f"{base_url}/api/weather", json=payload, headers=headers)

    assert response.status_code == 401

    expected_error_message = "Unauthorized"
    assert expected_error_message in response.json()["error"]

def test_forced_errors(base_url):
    payload = {
        "city": "lahore",
        "date": "2023-04-02",
        "temperature": "35",
        "humidity": "60"
    }

    headers = {
        "Content-Type": "application/json",
        "Authorization": "Bearer token"
    }

    params = {
        "error": "true"
    }

    response = requests.post(f"{base_url}/api/weather", json=payload, headers=headers, params=params)

    assert response.status_code == 401


