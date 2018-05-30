////////////////////////////////////////////////////////////////////////////////////////////////////
// DOMONITOR : Monitoring du système domotique Domocan ME sur tablette Android                    //
////////////////////////////////////////////////////////////////////////////////////////////////////
// MainActivity : Activité principal                                                              //
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

package inside.koala.kotlinpowaaaa

//region Imports
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import inside.koala.kotlinpowaaaa.Fragment.Levels.OutdoorFragment
import inside.koala.kotlinpowaaaa.Fragment.Levels.GrenierFragment
import inside.koala.kotlinpowaaaa.Fragment.Levels.RdcFragment
import inside.koala.kotlinpowaaaa.Fragment.Panels.AlarmFragment
import inside.koala.kotlinpowaaaa.Fragment.Rooms.*
import inside.koala.kotlinpowaaaa.Pager.MainViewPager
//endregion

////////////////////////////////////////////////////////////////////////////////////////////////////
// MONITORING ET CONTROLE POUR SYSTEME DOMOTIQUE DOMOCAN V1.0
////////////////////////////////////////////////////////////////////////////////////////////////////
class MainActivity : FragmentActivity (){

    //region Static
    companion object {
        enum class Levels(val index: Int)
        {
            GRENIER(0),
            RDC(1),
            OUTDOOR(2)
        }
        enum class Areas(val index: Int)
        {
            SAM(4),
            SALON(5),
            CUISINE(6),
            CHAMBRE_1(7),
            CHAMBRE_2(8),
            CHAMBRE_3(9),
            CHAMBRE_4(10),
            SDB(11),
            LABO(12),
            CELLIER(13)
        }
    }
    //endregion

    private lateinit var _viewPager : MainViewPager                                                  // Référence vers le ViewPager principal (affichage des différents écrans principaux)

    // #############################################################################################
    // CREATION DE L'ACTIVITE PRINCIPALE
    // #############################################################################################
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _viewPager = findViewById(R.id.viewPager)                                                    // Obtient la référence du ViewPager principal

        // -----------------------------------------------------------------------------------------
        // CONFIGURATION UTILISATEUR
        // -----------------------------------------------------------------------------------------

        // Il est impératif d'avoir au moins un niveau et un panneau. Les pièces/zooms/groupes sont facultatifs
        val levels = arrayOf(                                                                        // Création de la liste des niveaux de la maison avec en paramètres le nom complet de la classe du fragment ainsi que l'identifiant du layout utilisé
            SuperFragmentData(GrenierFragment::class.java.name, R.id.pool_fragment),
            SuperFragmentData(RdcFragment::class.java.name, R.id.rdc_fragment),
            SuperFragmentData(OutdoorFragment::class.java.name, R.id.outdoor_fragment)
        )

        val panels = arrayOf(                                                                        // Création de la liste des panneaux de contrôle
            SuperFragmentData(AlarmFragment::class.java.name, R.id.alarm_fragment)
        )

        // Si pas besoin des pièces, remplacer la déclaration de rooms par ceci :
        // val rooms = null
        //
        // La position d'une pièce dans la liste complète des fragments (_screens) est importante.
        // Ajouter un level ou un panneau va imposer de modifier l'enum pour
        // correspondre avec les index des fragments "pièces" dans la liste complète
        val rooms = arrayOf(                                                                         // Création de la liste des pièces/zooms/groupes si besoin
            SuperFragmentData(SamFragment::class.java.name, R.id.sam_fragment),
            SuperFragmentData(SalonFragment::class.java.name, R.id.salon_fragment),
            SuperFragmentData(CuisineFragment::class.java.name, R.id.cuisine_fragment),
            SuperFragmentData(Chambre1Fragment::class.java.name, R.id.chambre1_fragment),
            SuperFragmentData(Chambre2Fragment::class.java.name, R.id.chambre2_fragment),
            SuperFragmentData(Chambre3Fragment::class.java.name, R.id.chambre3_fragment),
            SuperFragmentData(Chambre4Fragment::class.java.name, R.id.chambre4_fragment),
            SuperFragmentData(SdbFragment::class.java.name, R.id.sdb_fragment),
            SuperFragmentData(LaboFragment::class.java.name, R.id.labo_fragment),
            SuperFragmentData(CellierFragment::class.java.name, R.id.cellier_fragment)
        )

        _viewPager.setAdapterAndParams(                                                              // Création de l'adaptateur du PageViewer
                supportFragmentManager,                                                              // - Référence vers le Fragment manager
                levels,                                                                              // - Liste des informations sur les fragments utilisés en tant que "niveau" de la maison
                panels,                                                                              // - Liste des informations sur les fragments utilisés en tant que "panneaux"
                rooms,                                                                               // - Liste des informations sur les fragments utilisés en tant que "pièces/zooms/groupes"
                1,                                                                        // - Index du niveau à afficher au démarrage (entre 0 et Nb de niveaux - 1)
                800,                                                                     // - Vitesse de l'animation "Scroll vertical"
                800,                                                                     // - Vitesse de l'animation "Scroll horizontal"
                800,                                                                       // - Vitesse de l'animation "Fade",
            30000                                                                     // - Ne marche pas encore
        )
    }
}