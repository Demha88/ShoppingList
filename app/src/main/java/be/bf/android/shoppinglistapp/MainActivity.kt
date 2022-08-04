package be.bf.android.shoppinglistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import be.bf.android.shoppinglistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setupActionBarWithNavController(findNavController(R.id.fragment))
    }

    // De fragent_add vers fragment_list

    override fun onSupportNavigateUp(): Boolean {
        val navControl = findNavController(R.id.fragment)
        return navControl.navigateUp() || super.onSupportNavigateUp()
    }


}