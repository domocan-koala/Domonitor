////////////////////////////////////////////////////////////////////////////////////////////////////
// DOMONITOR : Monitoring du système domotique Domocan ME sur tablette Android                    //
////////////////////////////////////////////////////////////////////////////////////////////////////
// MainPageAdapter : Adaptateur pour ViewPager
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

package inside.koala.kotlinpowaaaa.Pager

//region Imports
import android.content.Context;
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import inside.koala.kotlinpowaaaa.SuperFragmentData
//endregion

////////////////////////////////////////////////////////////////////////////////////////////////////
// MainPagerAdapter : Adaptateur pour ViewPager
////////////////////////////////////////////////////////////////////////////////////////////////////
class MainPagerAdapter(
        fragmentManager: FragmentManager,                                                            // Référence vers le Fragment Manager
        private val context: Context,                                                                // Référence vers le context
        private val screens: Array<SuperFragmentData>)                                               // Liste des noms de classe complets des fragments
    : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return Fragment.instantiate(context, screens[position].className)
    }

    override fun getCount(): Int {
        return screens.size
    }
}