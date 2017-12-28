package com.randalvance.workout

class Workout(var name: String, var description: String) {
    override fun toString() = description
}

val workouts = arrayOf(
        Workout("The Limb Loosener", "5 Handstand push-ups\n10 1-legged squats\n15 Pull ups"),
        Workout("Core Agony", "100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
        Workout("The Wimp Special", "5 Pull-ups\n10 Push-ups\n15 Squats")
)