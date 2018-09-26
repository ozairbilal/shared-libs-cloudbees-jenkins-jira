def call(jiraKey, BRANCH_NAME, releaseType, releaseEnv){
	def ticketStatus = jiraissueStatusFromKey(jiraKey)
	if (BRANCH_NAME == "Staging-Canary"){
		if ( ticketStatus == "deployed to qa"){
			jiraUpdateTransitiony(jiraKey, 221)
		}
		//jiraUpdateTransitionFromQAToBacklog(jiraKey, 171)
			jiraUpdateTransitiony(jiraKey, 171)
	}
	else if (BRANCH_NAME == "Pretest-Canary" || BRANCH_NAME == "Pretest-Main" ) {
		
			if (ticketStatus == "deployed to staging"){
				//231 is tranisition from Deployed to Staging to Promote to Staging
				jiraUpdateTransitiony(jiraKey, 231)
			}
			if (ticketStatus == "backlog"){
				return 0;
			}
			//241 is tranisition from Promote to Staging to Backlog
			jiraUpdateTransitiony(jiraKey, 241)
	}
}