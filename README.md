# Remind.me App
Aplikasi ini adalah aplikasi REST API mini project training alterra.

# API Documentations
### Users
- <strong>List Users</strong> `GET /api/v1/users`

<pre>
    $ curl --location --request GET "localhost:8080/api/v1/users"`
</pre>
<pre>
    <b>$ response:</b>

    [
        {
            "id": 1,
            "email": net.nandang@gmail.com,
            "full_name": "nandang s"
        },
        {
            "id": 2,
            "email": "ayu@gmail.com",
            "full_name": "ayu"
        }
    ]
</pre>
- <strong>Register New User</strong> `POST /api/v1/users/signup`
<pre>
    $ curl --location --request POST "localhost:8080/api/v1/users/signup" \
    > --header "Content-Type: application/json" \
    > --data-raw '{
    > "full_name": "alterra",
    > "email": "alterra@email.com",
    > "password": "passwordtest123"
    > }'
</pre>
<pre>
    <b>$ Response:</b>

    {
        "id": 5,
        "email": "alterra@email.com",
        "full_name": "alterra"
    }
</pre>

- <strong>Update User</strong> `PUT /api/v1/users/{id}`
<pre>
    $ curl --location --request PUT "localhost:8080/api/v1/users/5" \
    > --header "Content-Type: application/json" \
    > --data-raw '{
    > "full_name": "alta",
    > "email": "alta@email.com",
    > "password": "passwordtest123"
    > }'
</pre>
<pre>
    <b>$ Response:</b>

    {
        "id": 5,
        "email": "alta@email.com",
        "full_name": "alta"
    }
</pre>