def call(jiraKey, transitionKey){
                     httpRequest acceptType: 'APPLICATION_JSON', authentication: 'OzairJiraCredentials', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', httpMode: 'POST', requestBody: """{    
						"update": {        
								"duedate":[ {"set": "2019-05-22"} ]    },            
						"fields": { "customfield_13635": 
										{ "name": "ozair.bilal" , "key": "ozair.bilal"} 
										},    
						"transition": { "id": "181"    }
						}""", responseHandle: 'NONE', url: 'https://emaratech.atlassian.net/rest/api/latest/issue/'+jiraKey+'/transitions'
}

