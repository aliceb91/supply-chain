```mermaid
sequenceDiagram
    User->>API: Calls suppliers endpoint with no / direct query parameter
    API->>Domain: Request direct suppliers for user
    Domain->>UserRepo: Requests company for user
    UserRepo->>UserJson: Reads json file
    UserJson->>UserRepo: Pulls companyId for user from json
    UserRepo->>Domain: Returns companyId
    Domain->>SupplyChainRepo: Requests direct suppliers for companyId
    SupplyChainRepo->>CompanyJson: Reads json file
    CompanyJson->>SupplyChainRepo: Pulls supplier list from json
    SupplyChainRepo->>Domain: Returns direct suppliers as list of strings
    Domain->>API: Provides supplier list
    API->>User: Provides supplier list as JSON
```

[Mermaid URL](https://mermaid.live/edit#pako:eNp9k8FuwjAQRH9l5TNV7zkgVXABqW0E6i0XK96A28R213arCPHvXScEqKHNyUlm3niczUHUVqEohMfPiKbGpZY7kl1lgK83j_Qwnz-VqwIWsm09-Ohcq5E8oFHOahPgW4c9GAuPoDRhHYBB1IOTjMGANKKYwaSl7aQ2BWxSmg-T40JtLEH0k2mUsy9tZIPOnp0eats5afrMMQlPnrW3Q5pUHt55DY1u8aJMr3_Ry5hKntArdYZDQ7YbCDcxl0ohkrkyZxW2qWO_2PNd1uTuIWSYzM28xSj4p-GV4l7-2HVKhVbz98hq3qbmbW_2Lv1Isg34QNrsfHYOwzCVZL-0wiz-elLSCf-lSyHr7euLmIkOibmKB_iQ3JUIe-ywEgUvlaSPSlTmyDoZg932phZFoIgzEZ2SYRp2UTSy9fwUlQ6Wnsc_Yvgxjj_MDxgU)