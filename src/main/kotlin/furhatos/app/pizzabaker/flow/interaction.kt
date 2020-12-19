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

    onButton("Ask what pizza") {
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Great. What pizza are you gonna teach me?")
    }

    onButton("Pizza type received. goto Ingredients") {
        furhat.say("Ok!")

        goto(Ingredients)
    }
}

val Ingredients = state(Interaction) {
    val mentionedIngredients = ArrayList<String>()
    
    fun addToIngredients(ingredient : String) {
        if (!mentionedIngredients.contains(ingredient)) {
            mentionedIngredients.add(ingredient)
        }
    }

    onEntry {
        furhat.say("What ingredients do I need?")
    }

    onButton("Dough") {
        furhat.gesture(Gestures.Nod)
        furhat.say("Mmm... dough. ok")

        addToIngredients("dough")
    }

    onButton("Oil") {
        furhat.gesture(Gestures.Nod)
        furhat.say("Mmm... Oil. I got it")

        addToIngredients("oil")
    }

    onButton("Oregano") {
        furhat.gesture(Gestures.Blink)
        furhat.say("Mmm... Oregano. I got it")

        addToIngredients("dried oregano")
        furhat.gesture(Gestures.GazeAway)
    }

    onButton("Flour") {
        furhat.say("Mmm... Ok, flour. Then?")

        addToIngredients("flour")
        furhat.gesture(Gestures.BigSmile)
    }

    onButton("Cornmeal") {
        furhat.say("Mmm... then?")

        addToIngredients("cornmeal")
        furhat.gesture(Gestures.Smile)
    }

    onButton("Tomato sauce") {
        furhat.gesture(Gestures.Nod)
        furhat.say("Tomato sauce!")

        addToIngredients("tomato sauce")
        furhat.gesture(Gestures.BrowRaise)
    }

    onButton("Mozzarella") {
        furhat.gesture(Gestures.GazeAway)
        furhat.say("Noted! Mozzarella")

        addToIngredients("mozzarella")
        furhat.gesture(Gestures.BigSmile)
    }

    onButton("Mushrooms") {
        furhat.gesture(Gestures.Nod)
        furhat.say("Ok, mushrooms.")

        addToIngredients("mushrooms")
    }

    onButton("Little artichokes") {
        furhat.gesture(Gestures.ExpressDisgust)
        furhat.say("Ok, Anything else?")

        addToIngredients("little artichokes")
        furhat.gesture(Gestures.BrowRaise)
    }

    onButton("Ham") {
        furhat.say("I like it, cool.")
        furhat.say("Something else?")

        addToIngredients("ham")
        furhat.gesture(Gestures.BrowRaise)
    }

    onButton("Pineapple") {
        furhat.gesture(Gestures.ExpressDisgust)
        furhat.say("That's weird, cool.")
        furhat.gesture(Gestures.Oh)
        furhat.say("Something else?")

        addToIngredients("pineapple")
    }

    onButton("End Ingredients") {
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Ok, I'll need:")
        mentionedIngredients.forEach {
            furhat.say(it)
        }
        furhat.say("It seems I have everything!")
        furhat.say("Did you forget anything?")
        furhat.gesture(Gestures.BigSmile)
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

    onButton("Take the dough and make a ball") {
        furhat.gesture(Gestures.BigSmile)
        furhat.say("Ok, I have it")

        mentionedActions.add("Take the dough and make a round ball.")
    }

    onButton("Flatten dough") {
        furhat.gesture(Gestures.Nod)
        furhat.say("Ok, I have to flatten it.")

        mentionedActions.add("Flatten dough ball, and stretch out into a circle")
    }

    onButton("Add oil") {
        furhat.say("I have this extra vergine oil. It should work")

        mentionedActions.add("Brush dough top with olive oil")
    }

    onButton("Flour and dough on baking tray") {
        furhat.say("Oh ok... The flour helps the pizza to move from the baking tray. Then?")

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

    onButton("Bake 7min-300deg") {
        furhat.say("Oh cool! So I assume we are at the end.")
        furhat.say("Is it all?")
        furhat.gesture(Gestures.BrowRaise)

        mentionedActions.add("Bake the pizza in the oven for 7 minutes in 300 degrees Celsius")
    }

    onButton("Bake 12min-250deg") {
        furhat.say("Oh cool! So I assume we are at the end.")
        furhat.say("Is it all?")
        furhat.gesture(Gestures.BrowRaise)

        mentionedActions.add("Bake the pizza in the oven for 12 minutes in 250 degrees Celsius")
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
