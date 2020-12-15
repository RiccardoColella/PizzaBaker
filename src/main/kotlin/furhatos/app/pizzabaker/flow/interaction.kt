package furhatos.app.pizzabaker.flow

import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.*

val Start = state(Interaction) {
    onEntry {
        random(
                {   furhat.say("Hi there") },
                {   furhat.say("Oh, hello there") }
        )
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Can you teach me how to cook Pizza?")
    }

    onButton("Sure!") {
        furhat.say("Great. What pizza are you gonna teach me?")
        furhat.gesture(Gestures.BigSmile)
    }

    onButton("Go To Ingredients") {
        furhat.say("Ok!")

        goto(Ingredients)
    }
}

val Ingredients = state(Interaction) {
    val mentionedIngredients = ArrayList<String>()

    onEntry {
        furhat.say("What ingredients do I need?")
    }

    onButton("Oil") {
        furhat.say("Mmm... Oil. I got it")

        mentionedIngredients.add("oil")
    }

    onButton("Oregano") {
        furhat.say("Mmm... Oregano. I got it")

        mentionedIngredients.add("dried oregano")
    }

    onButton("Dough") {
        furhat.say("Mmm... dough. ok")

        mentionedIngredients.add("dough")
    }

    onButton("Cornmeal") {
        furhat.say("Mmm... then?")

        mentionedIngredients.add("cornmeal")
    }

    onButton("Flour") {
        furhat.say("Mmm... then?")

        mentionedIngredients.add("flour")
    }

    onButton("Tomato sauce") {
        furhat.say("Tomato sauce!")

        mentionedIngredients.add("tomato sauce")
    }

    onButton("Mozzarella") {
        furhat.say("Noted! Mozzarella")

        mentionedIngredients.add("mozzarella")
    }

    onButton("Mushrooms") {
        furhat.say("Ok, mushrooms.")

        mentionedIngredients.add("mushrooms")
    }

    onButton("Ham") {
        furhat.say("I like it, cool.")
        furhat.say("Something else?")

        mentionedIngredients.add("ham")
        furhat.gesture(Gestures.BrowRaise)
    }

    onButton("Pineapple") {
        furhat.say("That's weird, cool.")
        furhat.say("Something else?")

        mentionedIngredients.add("pineapple")
        furhat.gesture(Gestures.BrowRaise)
    }

    onButton("Little artichokes") {
        furhat.say("Ok, Anything else?")

        mentionedIngredients.add("little artichokes")
    }

    onButton("End Ingredients") {
        furhat.say("Ok, I'll need:")
        mentionedIngredients.forEach {
            furhat.say(it)
        }
        furhat.say("It seems I have everything!")
        furhat.say("Did you forget anything?")
        furhat.gesture(Gestures.BrowRaise)
    }

    onButton("Repeat List") {
        furhat.say("The ingredients that I noted are:")
        mentionedIngredients.forEach {
            furhat.say(it)
        }
        furhat.say("Do I need anything else?")
        furhat.gesture(Gestures.BrowRaise)
    }

    onButton("Go To Actions") {
        goto(Actions)
    }
}

val Actions = state(Interaction) {
    val mentionedActions = ArrayList<String>()

    onEntry {
        furhat.say("What is the procedure?")
    }

    onButton("Take the dough") {
        furhat.say("Ok, I have it")

        mentionedActions.add("Take the dough")
    }

    onButton("Flatten dough") {
        furhat.say("Ok, I have to flatten it.")

        mentionedActions.add("Flatten dough ball, and stretch out into a circle")
    }

    onButton("Add oil") {
        furhat.say("I have this extra vergine oil. It should work")

        mentionedActions.add("Brush dough top with olive oil")
    }

    onButton("Flour on pizza peel") {
        furhat.say("Oh ok... The four helps the pizza to move to the pizza stone. Then?")

        mentionedActions.add("Sprinkle baking tray with flour, put flattened dough on top")
    }

    onButton("Toppings") {
        furhat.say("So, ok. Here I add all the ingredients")

        mentionedActions.add("Spread the tomato sauce evenly on the pizza and sprinkle with toppings")
    }

    onButton("Oregano") {
        furhat.say("Ok, now I can add the oregano. Got it.")

        mentionedActions.add("Sprinkle dried oregano on the pizza")
    }

    onButton("Cornmeal on pizza stone") {
        furhat.say("Ok. Cornmeal on pizza stone")

        mentionedActions.add("Sprinkle cornmeal on pizza stone, slide pizza onto pizza stone in oven")
    }

    onButton("Bake 7min-350deg") {
        furhat.say("Oh cool! So I assume we are at the end.")
        furhat.say("Is it all?")
        furhat.gesture(Gestures.BrowRaise)

        mentionedActions.add("Bake the pizza in the oven for 7 minutes in 350 degrees Celsius")
    }

    onButton("Bake 5min-400deg") {
        furhat.say("Oh cool! So I assume we are at the end.")
        furhat.say("Is it all?")
        furhat.gesture(Gestures.BrowRaise)

        mentionedActions.add("Bake the pizza in the oven for 5 minutes in 400 degrees Celsius")
    }

    onButton("Unknown Ingredients") {
        furhat.say("I don't have that ingredient.")
        furhat.gesture(Gestures.Shake)

        furhat.say("Do I need to add it to the list of ingredients?")

    }

    onButton("End") {
        furhat.say("Cool! That's all! So, for baking pizza I have to:")

        mentionedActions.forEach {
            furhat.say(it)
        }

        goto(End)
    }
}

val End = state(Interaction) {
    onEntry {
        furhat.say("Amazing. Thank you very much for teaching me how to cook pizza! Super cool. See you!")
    }
}
