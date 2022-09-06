# Remind.me App
Aplikasi ini adalah aplikasi REST API mini project training alterra.

# API Documentations
### Users
- <strong>List Users</strong>

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
- <strong>Register New User</strong>
<pre>
    $ curl --location --request POST "localhost:8080/api/v1/users" \
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
- 