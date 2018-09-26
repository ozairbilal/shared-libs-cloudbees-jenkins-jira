def call(applicationVersion, releaseType){

		def checkIfTicketExists = jiraJqlSearch jql: "'Application Version'~${applicationVersion} AND type='Release Ticket'", site: 'JIRA'
		def jiraKey = ""
		def result = releaseType.split("-")
		releaseType = result[0]
		def releaseEnv = result[1]
		releaseEnv = releaseEnv.toLowerCase() 
		if (checkIfTicketExists.data.issues.size() > 0)
		{
			jiraKey = checkIfTicketExists.data.issues[0].key
		}
		else{
		//TODO: project: [id: '11201']
			if (releaseType == "hotfix"){
				def targetEnv
				if (releaseEnv == "canary"){
					targetEnv = '11705'
				}
				else{
					targetEnv = '11703'
				}
				def createIssue = [fields: 
										[project: [id: '11401'],
									   issuetype: [name: 'Release Ticket'],
									   summary: 'New JIRA Created from Jenkins CloudBees.',
									   description: 'New JIRA Created from Jenkins.',
									   //Application Version
									   customfield_11300: applicationVersion,
									   // Functional Area
									   // id:'10312' is Vision Core
									   customfield_10300: [[id:'10312'] ], 
									   // Releaseable Artifacts
									   customfield_10500: 'This is a dummy description of jenkins ticket',
									   //Release Type
									   //11602 is for hotfix
									   customfield_11400: [id: '11600'],
									   //SVN URL
									   customfield_10403: 'http://www.testjenkins.com',
									   //Hotfix merged
									   //id : 10901 is for NO
									   customfield_10900: [id: '10901'],
									   //Target Environment
									   customfield_11501: [id : targetEnv]
					]
				]
				jiraKey = jiraNewIssue issue: createIssue, site: 'JIRA'
				jiraKey = jiraKey.data.key.toString()
				jiraUpdateTransitionFromBacklogtoHotFixStaging(jiraKey, 211, releaseEnv )
			}
			else{
				def createIssue = [fields: 
										[project: [id: '11401'],
									   issuetype: [name: 'Release Ticket'],
									   summary: 'New JIRA Created from Jenkins CloudBees.',
									   description: 'New JIRA Created from Jenkins.',
									   //Application Version
									   customfield_11300: applicationVersion,
									   // Functional Area
									   // id:'10312' is Vision Core
									   customfield_10300: [[id:'10312'] ], 
									   // Releaseable Artifacts
									   customfield_10500: 'This is a dummy description of jenkins ticket',
									   //Release Type
									   //11600 is for planned
									   customfield_11400: [id: '11600'],
									   //SVN URL
									   customfield_10403: 'http://www.testjenkins.com',
									   //Hotfix merged
									   //id : 10901 is for NO
									   customfield_10900: [id: '10901']
					]
				]
				jiraKey = jiraNewIssue issue: createIssue, site: 'JIRA'
				jiraKey = jiraKey.data.key.toString()
				//161 is transition from backlog to promote to QA
				jiraUpdateTransitionFromBacklogToQA(jiraKey, 161)    
			}
		}
		return jiraKey
}