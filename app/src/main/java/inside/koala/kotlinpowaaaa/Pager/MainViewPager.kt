////////////////////////////////////////////////////////////////////////////////////////////////////
// DOMONITOR : Monitoring du système domotique Domocan ME sur tablette Android                    //
////////////////////////////////////////////////////////////////////////////////////////////////////
// MainViewPager : Gestion des écrans principaux                                                  //
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
import android.support.v4.app.FragmentManager
import android.content.Context
import android.media.Image
import android.provider.MediaStore
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import inside.koala.kotlinpowaaaa.SuperFragmentData
import android.view.animation.DecelerateInterpolator
import inside.koala.kotlinpowaaaa.Events.AreaClickEvent
import inside.koala.kotlinpowaaaa.Events.BackClickEvent
import inside.koala.kotlinpowaaaa.Events.GoToClickEvent
import inside.koala.kotlinpowaaaa.MainActivity
import java.util.*
import java.util.concurrent.ThreadPoolExecutor
import kotlin.concurrent.schedule

//endregion

////////////////////////////////////////////////////////////////////////////////////////////////////
// MainViewPager : Gestion des écrans principaux
////////////////////////////////////////////////////////////////////////////////////////////////////
class MainViewPager : ViewPager {

    //region Static
    companion object {
        enum class PageTransformers {                                                                // Effets de transistion disponibles
            HORIZONTAL,                                                                              // - Effet glissement horizontal
            VERTICAL,                                                                                // - Effet glissement vertical
            FADE                                                                                     // - Effet transparence
        }
    }
    //endregion

    //region Variables privées
    private var _lastLevelId = 0                                                                     // Index du dernier niveau
    private var _lastPanelId = 0                                                                     // Index du dernier panneau
    private var _mainLevelId = 0                                                                     // Index du niveau à afficher au démarrage
    private var _mainPanelId = 0                                                                     // Index du panneau principal
    private var startX : Float = 0F                                                                  // Mémorise la position du touch
    private var startY : Float = 0F                                                                  // Mémorise la position du touch
    private var horizontal : Boolean = false                                                         // Si on est en mode horizontal
    private var zoom : Boolean = false                                                               // Si on est dans une pièce
    private var closingZoom = false                                                                  // Pour échanger entre events
    private var _lastCurrentItem = 0                                                                 // Mémorise l'index du dernier ecran
    private var _disableScrollOverflowGuard = false                                                  // Sécurité pour ne pas scroller plusieurs page lors des long déplacement du doigt
    private var _scroller: FixedSpeedScroller                                                        // Référence vers le scroller
    private var _vScrollSpeed: Int = 1000                                                            // Vitesse de l'animation verticale
    private var _hScrollSpeed: Int = 1000                                                            // Vitesse de l'animation horizontale
    private var _fadeSpeed: Int = 1000                                                               // Vitesse de l'animation fade
    private var _fadeSourceId = 0                                                                    // Index de l'écran qui déclanche un zoom
    private lateinit var _screens: Array<SuperFragmentData>                                          // Liste des informations sur les fragments utilisés
    private var _returnHomeDelay: Long = 0
    //private var _returnHomeTimer : Timer? = null
    //endregion

    //region Constructeurs (et fonction init)
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init
    {
        // Configure le scroller
        val viewpager = ViewPager::class.java
        val scroller = viewpager.getDeclaredField("mScroller")
        scroller.isAccessible = true
        _scroller = FixedSpeedScroller(context,
                DecelerateInterpolator())
        scroller.set(this, _scroller)
    }
    //endregion

    //region Méthodes publiques
    fun setAdapterAndParams(
            fm: FragmentManager,                                                                     // Référence vers le Fragment Manager
            levels:Array<SuperFragmentData>,                                                         // Liste des informations sur les fragments utilisés en tant que "niveaux"
            panels : Array<SuperFragmentData>,                                                       // Liste des informations sur les fragments utilisés en tant que "panneaux"
            rooms : Array<SuperFragmentData>,                                                        // Liste des informations sur les fragments utilisés en tant que "pièces/zooms/groupes"
            mainLevelId : Int = 0,                                                                   // Index du niveau à afficher au démarrage
            vScrollSpeed: Int = 1000,                                                                // Vitesse de l'effet scroll vertiacl
            hScrollSpeed: Int = 1000,                                                                // Vitesse de l'effet scroll horizontal
            fadeSpeed: Int = 1000,                                                                   // Vitesse de l'effet transparence
            returnHomeDelay: Long) {

        _mainLevelId =                                                                               // Level principal
                if(mainLevelId < levels.size)                                                        // Si le level est valide
                    mainLevelId                                                                      // On garde le level
                else                                                                                 // Sinon
                    levels.size - 1                                                                  // On prend le dernier

        _mainPanelId = levels.size                                                                   // Index du panneau principal
        _lastLevelId = levels.size - 1                                                               // On mémorise l'index du dernier niveau (car après on ajoute les panneaux et cette information sera perdue)
        _lastPanelId = _lastLevelId + panels.size                                                    // Index du dernier panneau
        _vScrollSpeed = vScrollSpeed                                                                 // Vitesse de l'animation scroll vertical
        _hScrollSpeed = hScrollSpeed                                                                 // Vitesse de l'animation scroll horizontal
        _fadeSpeed = fadeSpeed                                                                       // Vitesse de l'animation fade
        _returnHomeDelay = returnHomeDelay                                                           // Nombre de seconde avant de retourner automatiquement sur l'écran principal
        _screens = levels + panels + rooms                                                           // Ajoute les panneaux et les pièces au bout de la liste des niveaux

        adapter =                                                                                    // Il nous faut un adaptateur
                MainPagerAdapter(                                                                    // Création de l'adaptateur qui a besoin des paramètres suivants
                        fm,                                                                          //  - Référence vers le FragmentManager
                        context,                                                                     //  - Référence vers le context
                        _screens                                                                     //  - Liste totale des informations sur les fragments (niveaux de la maison + panneaux latéraux [+ pièces])
                )

        offscreenPageLimit = _screens.size - 1                                                       // Nombre de pages gardées en mémoire, toutes parce qu'on est pas radin (^_^)

        ChangePageTransformer(PageTransformers.VERTICAL, _mainLevelId)                               // Chaange l'écran sans animation, l'event scroll en cours s'en chargera

        ////////////////////////////////////////////////////////////////////////////////////////////
        // CLICK SUR UNE ZONE DE ZOOM
        ////////////////////////////////////////////////////////////////////////////////////////////
        AreaClickEvent on{

            if(horizontal)
            {
                ChangePageTransformer(PageTransformers.VERTICAL, _mainLevelId)
                horizontal = false
            }
            zoom = true
            ChangePageTransformer(
                    PageTransformers.FADE,
                    (it.item.tag as MainActivity.Companion.Areas).index,
                    true
            )

        }

        ////////////////////////////////////////////////////////////////////////////////////////////
        // CLICK SUR UN BOUTON RETOUR VERS LE LEVEL
        ////////////////////////////////////////////////////////////////////////////////////////////
        BackClickEvent on {
            Log.d("vp", "fired")
            ChangePageTransformer(PageTransformers.FADE, _fadeSourceId, true)
            zoom = false
            closingZoom = true
        }

        ////////////////////////////////////////////////////////////////////////////////////////////
        // CLICK SUR UN BOUTON POUR CHANGER LE LEVEL
        ////////////////////////////////////////////////////////////////////////////////////////////
        GoToClickEvent on {
            if(horizontal)
            {
                ChangePageTransformer(PageTransformers.VERTICAL, _mainLevelId)
                horizontal = false
            }
            if(!zoom) {
                ChangePageTransformer(
                        PageTransformers.VERTICAL,
                        (it.item.tag as MainActivity.Companion.Levels).index,
                        true
                )
                horizontal = false
            }
        }
    }

    // #############################################################################################
    // QUAND LA PAGE A ETE SCROLLEE
    // #############################################################################################
    override fun onPageScrolled(position: Int, offset: Float, offsetPixels: Int)
    {
        if(_disableScrollOverflowGuard) {                                                            // Si le changement brutal de page est autorisé
            if(currentItem == _lastCurrentItem)
                _disableScrollOverflowGuard = false                                                  // Sinon on vérifie que l'utilisateur ne scroll pas deux pages en même temps
        }else{
            if (currentItem > _lastCurrentItem + 1) {                                                // Dans un sens
                currentItem = _lastCurrentItem + 1
                _lastCurrentItem = currentItem
                Log.d("ScrollGuard", "Locked")
            }
            if (currentItem < _lastCurrentItem - 1) {                                                // Comme dans l'autre
                currentItem = _lastCurrentItem - 1
                _lastCurrentItem = currentItem
                Log.d("ScrollGuard", "Locked")
            }

            if(position == 0) {                                                                      // Mémorise l'information pour la prochaine détection
                _lastCurrentItem = currentItem
            }
        }
        super.onPageScrolled(position, offset, offsetPixels)
    }

    // #############################################################################################
    // QUAND LA PAGE EST EN COURS DE SCROLL
    // #############################################################################################
    override fun onTouchEvent(event: MotionEvent): Boolean
    {


        if(event.action == MotionEvent.ACTION_DOWN) {
            startX = event.x
            startY = event.y
            return super.onTouchEvent(event)
        }else if(event.action == MotionEvent.ACTION_MOVE) {
            val difX : Float = event.x - startX
            val difY : Float = event.y - startY

            if(zoom) {
                event.setLocation(startX, startY)
                return super.onTouchEvent(event);
            }else if(closingZoom) {

                if (!horizontal) {
                    ChangePageTransformer(PageTransformers.VERTICAL)
                } else {
                    closingZoom = false
                }
            }

            // SCROLL VERTICAL
            if (Math.abs(difY) > Math.abs(difX))
            {
                if (Math.abs(difY) > 30) {
                    if (horizontal) {
                        // Si on est encore sur un panneau, on ignore le scroll
                        if (currentItem > _lastLevelId) {
                            //event.setLocation(x, y)
                            event.setLocation(startX, startY)
                            return super.onTouchEvent(event);
                        }
                        ChangePageTransformer(PageTransformers.VERTICAL, _mainLevelId)
                        horizontal = false
                    }

                    // Si on est sur le dernier niveau, on ignore le scroll car on ne peut descendre plus bas
                    if (difY < 0 && currentItem == _lastLevelId) {
                        event.setLocation(startX, startY)
                        return super.onTouchEvent(event);
                    } else {
                        // Scroll le niveau
                        val newX: Float = (difY * width * 1.5f / getHeight()) + startX
                        event.setLocation(newX, startY)
                        return super.onTouchEvent(event)
                    }
                }
            }
            // SCROLL HORIZONTAL
            else{
                // Filtre
                if (Math.abs(difX) > 30) {

                    // Mouvement du doigt vers la droite
                    if (difX > 0)
                    {
                        if(currentItem <= _lastLevelId)
                        {
                            event.setLocation(startX,startY);	// On bloque le mouvement
                            return super.onTouchEvent(event);
                        }
                    }
                    // Mouvement du doigt vers la gauche
                    else{
                        // Si on est sur le niveau qui doit afficher le premier panneau
                        if(horizontal) {
                            if(currentItem >= _lastPanelId)
                            {
                                event.setLocation(startX,startY);	// On bloque le mouvement
                                return super.onTouchEvent(event);
                            }
                        }else {
                            if(currentItem == _mainLevelId) {
                                horizontal = true

                                ChangePageTransformer(PageTransformers.HORIZONTAL, _lastLevelId)
                            }else {									// basculement refusé
                                event.setLocation(startX,startY);
                                return super.onTouchEvent(event);
                            }
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }
    //endregion

    //region Méthodes privées
    private fun ChangePageTransformer(pageTransformerEnum: PageTransformers, itemId: Int = -1, withEffect: Boolean = false)
    {
        var itemIdRework = if(itemId <= -1) currentItem else itemId
        //var allowEffect = withEffect

        if(withEffect && !zoom) {
            if (currentItem + 1 < itemIdRework) {
                itemIdRework--
            } else if (currentItem - 1 > itemIdRework) {
                itemIdRework++
            }
        }

        _disableScrollOverflowGuard = true

        Log.d("cpt","withEffect=$withEffect currentItem=$currentItem itemIdRework=$itemIdRework")
        when(pageTransformerEnum)
        {
            PageTransformers.VERTICAL ->
            {
                setPageTransformer(true, VerticalPageTransformer())
                _scroller.setScrollDuration(_vScrollSpeed);
            }
            PageTransformers.HORIZONTAL ->
            {
                var hiddenFragmentCount =
                        if(itemIdRework > currentItem)
                            itemIdRework - currentItem
                        else
                            currentItem - itemIdRework


                setPageTransformer(true,
                        HorizontalPageTransformer(
                                _screens[currentItem].fragmentId,
                                _screens[itemIdRework].fragmentId,
                                hiddenFragmentCount
                        )
                )
                _scroller.setScrollDuration(_hScrollSpeed);
                setCurrentItem(itemIdRework, false)
                return
            }
            PageTransformers.FADE ->
            {
                _fadeSourceId = currentItem
                setPageTransformer(true, FadePageTransformer(
                        _screens[currentItem].fragmentId,
                        _screens[itemIdRework].fragmentId
                ))
                _scroller.setScrollDuration(_fadeSpeed);
            }
        }

        if(withEffect) {
            currentItem = itemIdRework
            if(pageTransformerEnum != PageTransformers.HORIZONTAL)
                setCurrentItem(itemIdRework, true)
        }else{
            _lastCurrentItem = itemIdRework
            setCurrentItem(itemIdRework, false)
        }

    }
    //endregion

}


