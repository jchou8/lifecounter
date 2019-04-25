package edu.washington.jchou8.lifecounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val startLife = 15
    private val numPlayers = 4
    private var life = MutableList(numPlayers){startLife}

    private fun updateLife(index: Int, change: Int, view: TextView, loss: TextView) {
        life[index] += change
        view.text = life[index].toString()

        if (life[index] <= 0) {
            loss.text = "Player %d loses!".format(index + 1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewGroup = findViewById<ViewGroup>(R.id.playerLayout)
        val lossText = findViewById<TextView>(R.id.txt_loss)
        for (index in 0 until (viewGroup as ViewGroup).childCount) {
            val player = viewGroup.getChildAt(index)
            if (player is TextView) continue

            player.findViewById<TextView>(R.id.txt_plr).text = "%s %d".format(getString(R.string.player), index + 1)

            val lifeDisplay = player.findViewById<TextView>(R.id.txt_life)
            lifeDisplay.text = life[index].toString()

            player.findViewById<Button>(R.id.btn_incr).setOnClickListener{updateLife(index, 1, lifeDisplay, lossText)}
            player.findViewById<Button>(R.id.btn_incr5).setOnClickListener{updateLife(index, 5, lifeDisplay, lossText)}
            player.findViewById<Button>(R.id.btn_decr).setOnClickListener{updateLife(index, -5, lifeDisplay, lossText)}
            player.findViewById<Button>(R.id.btn_decr5).setOnClickListener{updateLife(index, -5, lifeDisplay, lossText)}
        }
    }
}
