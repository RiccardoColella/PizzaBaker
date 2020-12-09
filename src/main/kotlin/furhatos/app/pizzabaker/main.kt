package furhatos.app.pizzabaker

import furhatos.app.pizzabaker.flow.Idle
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class PizzaBakerSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}