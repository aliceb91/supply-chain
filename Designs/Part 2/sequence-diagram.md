```mermaid
sequenceDiagram
    User->>API: Calls directSupplier endpoint
    API->>Domain: Request direct supplier for user
    Domain->>UserRepo: Requests company for user
    UserRepo->>UserJson: Reads json file
    UserJson->>UserRepo: Pulls companyId for user from json
    UserRepo->>Domain: Returns companyId
    Domain->>SupplyChainRepo: Requests direct suppliers for companyId
    SupplyChainRepo->>CompanyJson: Reads json file
    CompanyJson->>SupplyChainRepo: Pulls supplier list from json
    SupplyChainRepo->>Domain: Returns direct suppliers as list of strings
    Domain->>Domain: Checks direct supplier list for target Id
    Domain->>SupplyChainRepo: Requests company data for target Id
    SupplyChainRepo->>CompanyJson: Reads json file
    CompanyJson->>SupplyChainRepo: Pulls supplier object from json
    SupplyChainRepo->>Domain: Returns the supplier data
    Domain->>API: Provides supplier data as a map
    API->>User: Provides supplier map as JSON
```

[Mermaid Url](https://mermaid.live/edit#pako:eNq1VMluwjAQ_RXLZ_oDOSBV4QJSW0TUWy7TeAKG2E69VEKIf-84C4uDKnFoTk7ylnkvsU-8MgJ5xh1-B9QVLiRsLahSM7o-HdqX-fx1vcxYDk3jmJAWK1-Etm0kWoZatEZq38MJR-iFUSB1xjZR0fmBwtzIqY1lgYR7To8mWvTaYGsuRMcqo1rQx4QxAgfOypnODIRje1qzWjZ4RcbXd-rrEHMM0ktxEWe1NapTmNhcE_lg9Q05idDVcsx3dJckSTpwnWsik7BJL-8BfyS8QTzy77Nemm8kfY4k5tQ1TTuZHVyvZGrmvJV665IeRoV8h9VhIjCMQQV4sFv07Ikexz9CgIdHEv_fofnaxzBPt-h3eBWJ4yeZuz22tuZHCnT3yFg4MAXt7S6Lv-cjAsEiflV8vPMZV2hJX9D-PkVyyWkMhSXPaCnAHkpe6jPhIHhTHHXFM28DznhoyXg8C3hWQ-PoKQrpjX3rD4zu3Dj_AiDUfPA)