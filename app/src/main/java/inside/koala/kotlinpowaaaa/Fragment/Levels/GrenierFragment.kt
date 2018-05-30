package inside.koala.kotlinpowaaaa.Fragment.Levels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inside.koala.kotlinpowaaaa.Events.GoToClickEvent
import inside.koala.kotlinpowaaaa.Fragment.SuperFragment
import inside.koala.kotlinpowaaaa.MainActivity
import inside.koala.kotlinpowaaaa.R
import kotlinx.android.synthetic.main.fragment_grenier.view.*

class GrenierFragment : SuperFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grenier, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // GO OUTDOOR
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibGrenierGoRdc){
            x = 5F
            y = 635F
            with(layoutParams)
            {
                width = 100
                height = 100
            }
            background = null
            alpha = 0.3F
            tag = MainActivity.Companion.Levels.RDC
            setOnClickListener(View.OnClickListener {
                GoToClickEvent(this).emit()
            })
        }
        super.onViewCreated(view, savedInstanceState)
    }
}