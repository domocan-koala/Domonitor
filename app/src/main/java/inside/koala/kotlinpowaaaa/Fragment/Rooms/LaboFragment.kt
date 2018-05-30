package inside.koala.kotlinpowaaaa.Fragment.Rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inside.koala.kotlinpowaaaa.Events.BackClickEvent
import inside.koala.kotlinpowaaaa.Fragment.SuperFragment
import inside.koala.kotlinpowaaaa.R
import kotlinx.android.synthetic.main.fragment_labo.view.*
import kotlinx.android.synthetic.main.fragment_sam.view.*
import kotlinx.android.synthetic.main.fragment_sdb.view.*

class LaboFragment : SuperFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        return inflater.inflate(R.layout.fragment_labo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(view.ibLaboBack){
            background = null
            setOnClickListener(View.OnClickListener {
                BackClickEvent(this).emit()
            })
        }

        super.onViewCreated(view, savedInstanceState)
    }

}
