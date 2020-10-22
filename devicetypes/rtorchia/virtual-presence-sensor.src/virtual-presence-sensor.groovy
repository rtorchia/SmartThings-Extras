/**
 *  State changes must be triggered by Automations or commands through webCoRE.
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
        name: "Virtual Presence Sensor",
        namespace: "rtorchia",
        author: "Ralph Torchia",
        vid: "fd9d406a-96d7-3d48-abc3-2f687dc63cfa",
        mnmn: "SmartThingsCommunity"
    )
    {
        capability "Presence Sensor"
        capability "Switch"

        command "arrived"
        command "departed"
        command "toggle"
    }

    tiles {}
}

def installed() {
    log.trace "Executing 'installed()'"
    sendEvent(name: "presence", value: "present")
}

def on() {
    sendEvent(name: "switch", value: "on")
    arrived()
}

def off() {
    sendEvent(name: "switch", value: "off")
    departed()
}

def arrived() {
    log.trace "Executing 'arrived()'"
    sendEvent(name: "presence", value: "present")
    sendEvent(name: "switch", value: "on")
}

def departed() {
    log.trace "Executing 'departed()'"
    sendEvent(name: "presence", value: "not present")
    sendEvent(name: "switch", value: "off")
}

def toggle() {
    if (device.currentValue('presence') == 'not present') {
        arrived()
    } else {
        departed()
    }
}