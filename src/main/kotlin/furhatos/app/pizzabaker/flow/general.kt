package furhatos.app.pizzabaker.flow

import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.*

val Idle: State = state {

    init {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        }
    }

    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        furhat.gesture(Gestures.Smile) // Do a smile

        goto(Start)
    }
}

val Interaction: State = state {

    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

    onButton("Not understanding") {
        random(
                { furhat.say("Could you repeat?")},
                { furhat.say("Sorry, I didn't get it")},
                { furhat.say("Pardon?")}
        )
        furhat.gesture(Gestures.BrowFrown)
        furhat.gesture(Gestures.Thoughtful)
    }

    onButton("Understood. Then?") {
        random(
                { furhat.say("Ok, got it.") },
                { furhat.say("Great.") },
                { furhat.say("I'm writing it.")}
        )
        furhat.gesture(Gestures.Nod)
        random(
                { furhat.ask("Then?") },
                { furhat.ask("What's next") },
                { furhat.ask("What's the following step?") }

            )
    }


}