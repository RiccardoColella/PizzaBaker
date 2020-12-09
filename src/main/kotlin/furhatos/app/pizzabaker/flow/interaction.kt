package furhatos.app.pizzabaker.flow

import furhatos.app.pizzabaker.nlu.*
import furhatos.app.pizzabaker.order
import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val Start = state(Interaction) {
    onEntry {
        random(
                {   furhat.say("Hi there") },
                {   furhat.say("Oh, hello there") }
        )
        furhat.say("I'm selling some fruit here!")

        goto(TakingOrder)
    }
}

val Options = state(Interaction) {
    onResponse<BuyFruit> {
        val fruits = it.intent.fruits
        if (fruits != null) {
            goto(OrderReceived(fruits))
        }
        else {
            propagate()
        }
    }

    onResponse<RequestOptions> {
        furhat.say("We have ${Fruit().optionsToText()}")
        furhat.ask("Do you want some?")
    }

    onResponse<Yes> {
        random(
                { furhat.ask("What kind of fruit do you want?") },
                { furhat.ask("What type of fruit?") }
        )
    }
}

val TakingOrder = state(Options) {
    onEntry {
        random(
                { furhat.ask("How about some fruits?") },
                { furhat.ask("Do you want some fruits?") }
        )
    }

    onResponse<No> {
        furhat.say("Okay, that's a shame. Have a splendid day!")
        goto(Idle)
    }
}

fun OrderReceived(fruits: FruitList) : State = state(Options) {
    onEntry {
        furhat.say("${fruits.text}, what a lovely choice!")
        fruits.list.forEach {
            users.current.order.fruits.list.add(it)
        }
        furhat.ask("Anything else?")
    }

    onReentry {
        furhat.ask("Did you want something else?")
    }

    onResponse<No> {
        furhat.say("Okay, here is your order of ${users.current.order.fruits}.")
        goto(Confirmation)
    }
}

val Confirmation = state(Interaction) {
    onEntry {
        furhat.ask("Do you want to confirm your order?")
    }

    onResponse<Yes> {
        furhat.say("Okay, here is your order of ${users.current.order.fruits}.")
        users.current.order.fruits = FruitList()
    }

    onResponse<No> {
        furhat.say("Ok, I'll delete it. Come back if you want to order something else!")
        users.current.order.fruits = FruitList()
    }
}