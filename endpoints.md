GET /suppliers?type=direct

HTTP 200

Example response:
```
{

}
```

Possible values for the `type` query string:

1. Direct
Returns only direct suppliers

Any other values are ignored (direct will be assumed)

If not Provided, direct is assumed.