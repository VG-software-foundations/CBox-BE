# CBox-BE
CBox is a fast, convenient cloud storage with open Source code.
Our main goal is to provide users with privacy, you can mark your data as "private" and no one will access it.

Project is using next technologies:
* Spring Boot 3
* Java 21
* PostgreSQL
* Redis

# Use-Case Diagrams: 
 # Guest Scenario
   ![Guest Scenario](docs/Guest.png)
 # Admin Scenario
   ![Admin Scenario](docs/Admin.png)
 # User Scenario
   ![User Scenario](docs/User.png)
 # Document Scenario
   ![Document Scenario](docs/Document.png)


# DB ERD
![ERD](docs/Diagram.jpeg)

# API Description
{
  "openapi": "3.0.1",
  "info": {
    "title": "OpenAPI definition",
    "version": "v0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/users": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "operationId": "findAll",
        "parameters": [
          {
            "name": "page",
            "in": "query",
            "required": false,
            "schema": {
              "minimum": 1,
              "type": "integer",
              "format": "int32",
              "default": 1
            }
          },
          {
            "name": "limit",
            "in": "query",
            "required": false,
            "schema": {
              "maximum": 100,
              "minimum": 1,
              "type": "integer",
              "format": "int32",
              "default": 10
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/PageResponseUserReadDto"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "user-controller"
        ],
        "operationId": "update",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/UserCreateEditDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UserReadDto"
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "user-controller"
        ],
        "operationId": "create",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/UserCreateEditDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "201": {
            "description": "Created",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UserReadDto"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "user-controller"
        ],
        "operationId": "delete",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/UserCreateEditDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK"
          }
        }
      }
    },
    "/users/{id}": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "operationId": "findById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "string"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UserReadDto"
                }
              }
            }
          }
        }
      }
    },
    "/users/me": {
      "get": {
        "tags": [
          "user-controller"
        ],
        "operationId": "findSelf",
        "parameters": [
          {
            "name": "user",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/UserCreateEditDto"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UserReadDto"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "UserCreateEditDto": {
        "type": "object",
        "properties": {
          "id": {
            "type": "string",
            "format": "uuid"
          },
          "email": {
            "type": "string"
          },
          "firstName": {
            "type": "string"
          },
          "secondName": {
            "type": "string"
          },
          "username": {
            "type": "string"
          },
          "phoneNumber": {
            "type": "string"
          }
        }
      },
      "UserReadDto": {
        "type": "object",
        "properties": {
          "email": {
            "type": "string"
          },
          "firstName": {
            "type": "string"
          },
          "secondName": {
            "type": "string"
          },
          "username": {
            "type": "string"
          },
          "phoneNumber": {
            "type": "string"
          }
        }
      },
      "Metadata": {
        "type": "object",
        "properties": {
          "total_elements": {
            "type": "integer",
            "format": "int64"
          },
          "has_next": {
            "type": "boolean"
          },
          "page": {
            "type": "integer",
            "format": "int32"
          },
          "size": {
            "type": "integer",
            "format": "int32"
          }
        }
      },
      "PageResponseUserReadDto": {
        "type": "object",
        "properties": {
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/UserReadDto"
            }
          },
          "metadata": {
            "$ref": "#/components/schemas/Metadata"
          }
        }
      }
    }
  }
}
