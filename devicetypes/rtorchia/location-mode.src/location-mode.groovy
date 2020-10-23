/**
 *  Location Mode
 *
 *  Copyright 2020 Ralph Torchia
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 */
metadata {
	definition (
        name: "Location Mode",
        namespace: "rtorchia",
        author: "Ralph Torchia",
        vid: "a742f165-e0ce-3412-bff7-acefc0938595",
        mnmn: "SmartThingsCommunity",
        cstHandler: true
    )
    {
		capability "pizzafiber16443.generalStatus"
	}

	tiles {}
}

// parse events into attributes
def setGeneralStatus() {
	def message = location.currentMode
	log.debug "Location mode set to: ${message}"
	sendEvent(name: "generalStatus", value: "${message}")
}

// handle commands
def updated() {
	log.debug "Executing 'updated()'"
	log.debug "Current mode: ${location.currentMode}"
    sendEvent(name: "generalStatus", value: "${location.currentMode}")
    runEvery1Minute(setGeneralStatus)
}

def installed() {
	log.debug "Executing 'installed()'"
	log.debug "Current mode: ${location.currentMode}"
    sendEvent(name: "generalStatus", value: "${location.currentMode}")
}

def initialize() {
    runEvery1Minute(setGeneralStatus)
}