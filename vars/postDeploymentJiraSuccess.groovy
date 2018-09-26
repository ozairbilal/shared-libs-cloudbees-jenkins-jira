def call(jiraKey, BRANCH_NAME, releaseType){

		if (BRANCH_NAME == "Staging-Canary"){
			 def ticketStatus = jiraissueStatusFromKey(jiraKey)
			echo ticketStatus
			if (ticketStatus == "backlog"){
					//161 is transition from backlog to promote to QA
					jiraUpdateTransitionFromBacklogToQA(jiraKey, 161)    
			}
			else if ( ticketStatus == "deployed to qa"){
				jiraUpdateTransitiony(jiraKey, 221)
				}
			//181 is tranisition from promote to QA to Deployed to QA
			jiraUpdateTransitionFromQAToDeployed(jiraKey, 181)
			
		}
		

		else if (BRANCH_NAME == "Pretest-Canary" || BRANCH_NAME == "Pretest-Main"){
			def ticketStatus = jiraissueStatusFromKey(jiraKey)
			if (releaseType == "hotfix"){
				if (ticketStatus == "deployed to staging"){
				//231 is tranisition from Deployed to Staging to Promote to Staging
				jiraUpdateTransitiony(jiraKey, 231)
				}
				else if (ticketStatus == "backlog"){
					jiraUpdateTransitionFromBacklogtoHotFixStaging(jiraKey, 211, releaseEnv )
				}
				//251 is tranisition from promote to Hotfix  to Build Ready
				jiraUpdateTransitiony(jiraKey, 251)
				//191 is tranisition from promote to Hotfix  to Promoted
				jiraUpdateTransitiony(jiraKey, 191)
			}
			else if (releaseType == "planned"){
				if (ticketStatus  == "backlog"){
					//161 is transition from backlog to promote to QA'
					jiraUpdateTransitionFromBacklogToQA(jiraKey, 161)    
					//181 is tranisition from promote to QA to Deployed to QA
					jiraUpdateTransitionFromQAToDeployed(jiraKey, 181)
					jiraUpdateTransitionFromDeploytoQAToStaging(jiraKey, 141)
				}
				else if (ticketStatus == "deployed to qa"){
					jiraUpdateTransitionFromDeploytoQAToStaging(jiraKey, 141)
					
				}
				else if (ticketStatus == "deployed to staging"){
					//231 is tranisition from Deployed to Staging to Promote to Staging
					jiraUpdateTransitiony(jiraKey, 231)
				}
				//251 is tranisition from promote to Staging  to Build Ready
				jiraUpdateTransitiony(jiraKey, 251)
				//191 is tranisition from promote to Hotfix  to Promoted(after sub tasks are closed/TestFlow doesnt have sub tasks)
				jiraUpdateTransitiony(jiraKey, 191)
			}
		}
}
