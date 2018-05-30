////////////////////////////////////////////////////////////////////////////////////////////////////
// DOMONITOR : Monitoring du système domotique Domocan ME sur tablette Android                    //
////////////////////////////////////////////////////////////////////////////////////////////////////
// HorizontalPageTransformer : Effet horizontal lors d'un changement d'écran                      //
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
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
//endregion

////////////////////////////////////////////////////////////////////////////////////////////////////
// HorizontalPageTransformer : Effet Horizontal
////////////////////////////////////////////////////////////////////////////////////////////////////
class HorizontalPageTransformer(
        val sourceFragmentId: Int,                                                                   // Identifiant du layout du fragment qui lance l'animation
        val targetFragmentId: Int,                                                                   // Identifiant du layout à atteindre
        val hiddenFragmentCount: Int)                                                                // Nombre de fragments entre la source et la destination à masquer
    : ViewPager.PageTransformer
{
    override fun transformPage(page: View, position: Float) {

       if(position <= 1.0F)
       {
            with(page)
            {
                alpha =
                    if(id == targetFragmentId && id != sourceFragmentId)
                        0.0F
                    else
                        1.0F

                translationY = 0.0F
                translationX =
                    if(id == sourceFragmentId)
                    {
                        width.toFloat() * hiddenFragmentCount
                    }else
                        0.0F
            }
        }
    }
}

