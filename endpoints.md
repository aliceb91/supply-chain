## GET /suppliers?type=direct

HTTP 200

Example response:
```
{
    "companyId": "ZC789,
    "directSuppliers": [ZS456]
}
```

Possible values for the `type` query string:

1. Direct: Returns only direct suppliers

Any other values are ignored (direct will be assumed)

If not Provided, direct is assumed.

Headers

1. userId: ID of the current user

## GET /target_supplier?type=direct

HTTP 200

Example response:
```
{
    "companyId": "ZS456",
    "buyers": [],
    "suppliers": []
}
```

Possible values for the `type` query string:

1. Direct: Returns only a direct supplier

Any other values are ignored (direct will be assumed)

If not Provided, direct is assumed.

Headers

1. userId: ID of the current user

2. targetCompanyId: ID of the desired supplier

## GET /add_direct_supplier

HTTP 200

Example response:
```
{
    "companyId": "ZS456",
    "buyers": [],
    "suppliers": ["ZS456", "ZS654"]
}
```

Headers

1. userId: ID of the current user

2. targetCompanyId: ID of the supplier to be added