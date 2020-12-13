package furhatos.app.pizzabaker.flow

import furhatos.flow.kotlin.*
import furhatos.nlu.common.*

val Start = state(Interaction) {
    onEntry {
        random(
                {   furhat.say("Hi there") },
                {   furhat.say("Oh, hello there") }
        )
        furhat.say("Can you teach me how to cook Pizza?")
    }

    onButton("Sure!") {
        furhat.say("Great")

        goto(Ingredients)
    }

    onResponse<Yes> {
        furhat.say("Great!" )

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

    onButton("Dough") {
        furhat.say("Mmm... dough. ok")

        mentionedIngredients.add("dough")
    }

    onButton("Cornmeal") {
        furhat.say("Mmm... then?")

        mentionedIngredients.add("cornmeal")
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
        furhat.say("Ok")

        mentionedIngredients.add("mushrooms")
    }

    onButton("Ham") {
        furhat.say("I like it, cool")

        mentionedIngredients.add("ham")
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

        mentionedActions.add("Flatten dough ball, and stretch out into a round")
    }

    onButton("Add oil") {
        furhat.say("I have this extra vergine oil. It should work")

        mentionedActions.add("Brush dough top with olive oil")
    }

    onButton("Cornmeal on pizza peel") {
        furhat.say("Oh ok... The cornmeal helps the pizza to move to the pizza stone. Then?")

        mentionedActions.add("Sprinkle pizza peel with corn meal, put flattened dough on top")
    }

    onButton("Toppings") {
        furhat.say("So, ok. Here I add all the ingredients")

        mentionedActions.add("Spread with tomato sauce and sprinkle with toppings")
    }

    onButton("Cornmeal on pizza stone") {
        furhat.say("Ok. Cornmeal on pizza stone")

        mentionedActions.add("Sprinkle cornmeal on pizza stone, slide pizza onto pizza stone in oven")
    }

    onButton("Bake") {
        furhat.say("Oh cool! So I assume we are at the end. Is it all?")
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
