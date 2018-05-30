////////////////////////////////////////////////////////////////////////////////////////////////////
// DOMONITOR : Monitoring du système domotique Domocan ME sur tablette Android                    //
////////////////////////////////////////////////////////////////////////////////////////////////////
// RdcFragment : Ecran RDC                                                                        //
////////////////////////////////////////////////////////////////////////////////////////////////////
// Auteur(s)            : Bigonoff, Koala                                                         //
// URL                  : https://www.abcelectronique.com/bigonoff/forum/index.php                //
////////////////////////////////////////////////////////////////////////////////////////////////////
// Historique                                                                                     //
// ----------                                                                                     //
// V0.00    28/05/2018  : Première version en cours de création                                   //
////////////////////////////////////////////////////////////////////////////////////////////////////
// Licence d'utilisation                                                                          //
// ---------------------                                                                          //
// Ce document est fourni tel quel, sans aucune garantie et sans recours d'aucune sorte           //
// en cas de préjudice direct ou indirect lié ou non à son utilisation.                           //
// Vous avez le droit d'utiliser et/ou de modifier ce document dans le cadre d'une                //
// utilisation privée, publique, ou commerciale.                                                  //
// Vous ne pouvez distribuer ce document qu'accompagné du présent commentaire original            //
// intégral incluant l'historique et les références de l'auteur.                                  //
// En cas de distribution d'une version modifiée, vous devrez ajouter au présent                  //
// commentaire la liste des points modifiés, la raison de la modification, sa date,               //
// ainsi que l'identification du modificateur.                                                    //
// Il n'est pas autorisé de vendre ce document ou une version modifiée de celui-ci.               //
// Les codes exécutables produits à partir de ce document sont soumis à cette licence             //
// d'utilsation.                                                                                  //
// Il n'est pas autorisé de distribuer les codes exécutables sans distribuer les                  //
// fichiers sources ayant servi à les générer.                                                    //
// En cas de traduction, seule la version originale française obtenue à partir du site            //
// de l'auteur fait référence. Il en va de même concernant les modifications.                     //
// L'auteur s'est efforcé de réaliser cette librairie d'après les documents techniques            //
// officiels. Toute ressemblance avec un document existant ne serait que pure                     //
// coïncidence.                                                                                   //
////////////////////////////////////////////////////////////////////////////////////////////////////

package inside.koala.kotlinpowaaaa.Fragment.Levels

//region Imports
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inside.koala.kotlinpowaaaa.Events.AreaClickEvent
import inside.koala.kotlinpowaaaa.Events.GoToClickEvent
import inside.koala.kotlinpowaaaa.R
import inside.koala.kotlinpowaaaa.Fragment.SuperFragment
import inside.koala.kotlinpowaaaa.MainActivity
import kotlinx.android.synthetic.main.fragment_rdc.view.*
//endregion

////////////////////////////////////////////////////////////////////////////////////////////////////
// RdcFragment : Ecran RDC
////////////////////////////////////////////////////////////////////////////////////////////////////
class RdcFragment : SuperFragment() {

    // #############################################################################################
    // CREATION DE LA VUE
    // #############################################################################################
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rdc, container, false)
    }

    // #############################################################################################
    // APRES LA CREATION DE LA VUE
    // #############################################################################################
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA SALLE A MANGER
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibSamArea){
            x = 295F
            y = 110F
            background = null
            tag = MainActivity.Companion.Areas.SAM
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA CUISINE
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibCuisineArea){
            x = 90F
            y = 230F
            with(layoutParams)
            {
                width = 180
                height = 260
            }
            background = null
            tag = MainActivity.Companion.Areas.CUISINE
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA SALON
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibSalonArea){
            x = 185F
            y = 520F
            with(layoutParams)
            {
                width = 350
                height = 120
            }
            background = null
            tag = MainActivity.Companion.Areas.SALON
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA CHAMBRE 1
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibChambre1Area){
            x = 790F
            y = 390F
            with(layoutParams)
            {
                width = 240
                height = 240
            }
            background = null
            tag = MainActivity.Companion.Areas.CHAMBRE_1
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA CHAMBRE 2
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibChambre2Area){
            x = 1000F
            y = 95F
            with(layoutParams)
            {
                width = 210
                height = 220
            }
            background = null
            tag = MainActivity.Companion.Areas.CHAMBRE_2
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA CHAMBRE 3
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibChambre3Area){
            x = 730F
            y = 95F
            with(layoutParams)
            {
                width = 200
                height = 220
            }
            background = null
            tag = MainActivity.Companion.Areas.CHAMBRE_3
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA CHAMBRE 4
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibChambre4Area){
            x = 510F
            y = 95F
            with(layoutParams)
            {
                width = 200
                height = 220
            }
            background = null
            tag = MainActivity.Companion.Areas.CHAMBRE_4
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA SALLE DE BAIN
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibSdbArea){
            x = 1040F
            y = 310F
            with(layoutParams)
            {
                width = 180
                height = 180
            }
            background = null
            tag = MainActivity.Companion.Areas.SDB
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA LABO
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibLaboArea){
            x = 1040F
            y = 480F
            with(layoutParams)
            {
                width = 180
                height = 180
            }
            alpha = 0.3F
            background = null
            tag = MainActivity.Companion.Areas.LABO
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // AREA CELLIER
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibCellierArea){
            x = 645F
            y = 495F
            with(layoutParams)
            {
                width = 130
                height = 170
            }
            background = null
            tag = MainActivity.Companion.Areas.CELLIER
            setOnClickListener(View.OnClickListener {
                AreaClickEvent(this).emit()
            })
        }




        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // GO GRENIER
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibGoGrenier){
            x = 5F
            y = 5F
            with(layoutParams)
            {
                width = 100
                height = 100
            }
            background = null
            alpha = 0.3F
            tag = MainActivity.Companion.Levels.GRENIER
            setOnClickListener(View.OnClickListener {
                GoToClickEvent(this).emit()
            })
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // GO OUTDOOR
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        with(view.ibGoOutdoor){
            x = 5F
            y = 635F
            with(layoutParams)
            {
                width = 100
                height = 100
            }
            alpha = 0.3F
            background = null
            tag = MainActivity.Companion.Levels.OUTDOOR
            setOnClickListener(View.OnClickListener {
                GoToClickEvent(this).emit()
            })
        }

        super.onViewCreated(view, savedInstanceState)
    }

}