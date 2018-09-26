def call(jiraKey, transitionKey){
                     httpRequest acceptType: 'APPLICATION_JSON', authentication: 'OzairJiraCredentials', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: """{
                        
                                    "fields": {
                                                "customfield_11501": { "value": "Canary Staging" , "id": "11704"}
                                                },
                            "transition": {
							"id": ${transitionKey}
                            }
                        }""", responseHandle: 'NONE', url: 'https://emaratech.atlassian.net/rest/api/latest/issue/'+jiraKey+'/transitions'
}