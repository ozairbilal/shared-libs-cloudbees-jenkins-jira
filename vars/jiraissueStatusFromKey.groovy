def call(jiraKey){
					 def result = jiraJqlSearch jql: "key = ${jiraKey} AND type='Release Ticket'", site: 'JIRA'
					 //returns the status of release ticket
					result =  result.data.issues[0].fields.status.name.toString()
					return result.toLowerCase()
					}