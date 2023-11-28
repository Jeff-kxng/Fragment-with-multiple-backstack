package sok.sokong.fragmentwithmultiplebackstack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, WelcomeFragment())
                .addToBackStack(null) // Add the initial fragment to the back stack
                .commit()
        }

        setActionBarTitle("Quiz App") // Set the title initially
    }

    // Function to set the title in the toolbar
    fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }
}
