package org.kyll.myserver.base.gis.ctrl;

import org.kyll.myserver.base.gis.entity.OlInteraction;
import org.kyll.myserver.base.gis.service.OlInteractionService;
import org.kyll.myserver.base.gis.vo.ThematicVo;
import org.kyll.myserver.base.util.ConstUtils;
import org.kyll.myserver.base.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2015-10-07 8:40
 */
@Controller
@Scope("request")
public class OlInteractionCtrl {
	@Autowired
	private OlInteractionService olInteractionService;

	@RequestMapping("/gis/interaction/input.ctrl")
	public void input(Long mapId, HttpServletResponse response) throws Exception {
		ThematicVo olInteractionVo = new ThematicVo();

		List<OlInteraction> olInteractionList = olInteractionService.getByOlMap(mapId, null);
		for (OlInteraction olInteraction : olInteractionList) {
			String interactionClassName = olInteraction.getInteractionClassName();
			if (ConstUtils.GIS_OL_INTERACTION_DOUBLECLICKZOOM.equals(interactionClassName)) {
				olInteractionVo.setInteractionDoubleClickZoom(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionDoubleClickZoomDelta(olInteraction.getDelta());
				olInteractionVo.setInteractionDoubleClickZoomDuration(olInteraction.getDuration());
			} else if (ConstUtils.GIS_OL_INTERACTION_DRAGBOX.equals(interactionClassName)) {
				olInteractionVo.setInteractionDragBox(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionDragBoxCondition(olInteraction.getCondition());
				olInteractionVo.setInteractionDragBoxStyle(olInteraction.getStyle());
			} else if (ConstUtils.GIS_OL_INTERACTION_DRAGPAN.equals(interactionClassName)) {
				olInteractionVo.setInteractionDragPan(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionDragPanKineticDecay(olInteraction.getKineticDecay());
				olInteractionVo.setInteractionDragPanKineticDelay(olInteraction.getKineticDelay());
				olInteractionVo.setInteractionDragPanKineticMinVelocity(olInteraction.getKineticMinVelocity());
			} else if (ConstUtils.GIS_OL_INTERACTION_DRAGROTATE.equals(interactionClassName)) {
				olInteractionVo.setInteractionDragRotate(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionDragRotateCondition(olInteraction.getCondition());
				olInteractionVo.setInteractionDragRotateDuration(olInteraction.getDuration());
			} else if (ConstUtils.GIS_OL_INTERACTION_DRAGROTATEANDZOOM.equals(interactionClassName)) {
				olInteractionVo.setInteractionDragRotateAndZoom(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionDragRotateAndZoomCondition(olInteraction.getCondition());
				olInteractionVo.setInteractionDragRotateAndZoomDuration(olInteraction.getDuration());
			} else if (ConstUtils.GIS_OL_INTERACTION_DRAGZOOM.equals(interactionClassName)) {
				olInteractionVo.setInteractionDragZoom(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionDragZoomCondition(olInteraction.getCondition());
				olInteractionVo.setInteractionDragZoomDuration(olInteraction.getDuration());
				olInteractionVo.setInteractionDragZoomStyle(olInteraction.getStyle());
			} else if (ConstUtils.GIS_OL_INTERACTION_KEYBOARDPAN.equals(interactionClassName)) {
				olInteractionVo.setInteractionKeyboardPan(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionKeyboardPanDuration(olInteraction.getDuration());
				olInteractionVo.setInteractionKeyboardPanPixelDelta(olInteraction.getPixelDelta());
			} else if (ConstUtils.GIS_OL_INTERACTION_KEYBOARDZOOM.equals(interactionClassName)) {
				olInteractionVo.setInteractionKeyboardZoom(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionKeyboardZoomDelta(olInteraction.getDelta());
				olInteractionVo.setInteractionKeyboardZoomDuration(olInteraction.getDuration());
			} else if (ConstUtils.GIS_OL_INTERACTION_MOUSEWHEELZOOM.equals(interactionClassName)) {
				olInteractionVo.setInteractionMouseWheelZoom(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionMouseWheelZoomDuration(olInteraction.getDuration());
			} else if (ConstUtils.GIS_OL_INTERACTION_PINCHROTATE.equals(interactionClassName)) {
				olInteractionVo.setInteractionPinchRotate(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionPinchRotateDuration(olInteraction.getDuration());
				olInteractionVo.setInteractionPinchRotateThreshold(olInteraction.getThreshold());
			} else if (ConstUtils.GIS_OL_INTERACTION_PINCHZOOM.equals(interactionClassName)) {
				olInteractionVo.setInteractionPinchZoom(olInteraction.getInteractionEnabled());
				olInteractionVo.setInteractionPinchZoomDuration(olInteraction.getDuration());
			}
		}

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.convert(olInteractionVo));
	}

	@RequestMapping("/gis/interaction/save.ctrl")
	public void save(ThematicVo entityVo, HttpServletResponse response) throws Exception {
		List<OlInteraction> olInteractionList = new ArrayList<>();

		OlInteraction olInteractionDoubleClickZoom = new OlInteraction();
		olInteractionDoubleClickZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DOUBLECLICKZOOM);
		olInteractionDoubleClickZoom.setInteractionEnabled(entityVo.getInteractionDoubleClickZoom());
		olInteractionDoubleClickZoom.setDelta(entityVo.getInteractionDoubleClickZoomDelta());
		olInteractionDoubleClickZoom.setDuration(entityVo.getInteractionDoubleClickZoomDuration());
		olInteractionList.add(olInteractionDoubleClickZoom);

		OlInteraction olInteractionDragBox = new OlInteraction();
		olInteractionDragBox.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGBOX);
		olInteractionDragBox.setInteractionEnabled(entityVo.getInteractionDragBox());
		olInteractionDragBox.setCondition(entityVo.getInteractionDragBoxCondition());
		olInteractionDragBox.setStyle(entityVo.getInteractionDragBoxStyle());
		olInteractionList.add(olInteractionDragBox);

		OlInteraction olInteractionDragPan = new OlInteraction();
		olInteractionDragPan.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGPAN);
		olInteractionDragPan.setInteractionEnabled(entityVo.getInteractionDragPan());
		olInteractionDragPan.setKineticDecay(entityVo.getInteractionDragPanKineticDecay());
		olInteractionDragPan.setKineticDelay(entityVo.getInteractionDragPanKineticDelay());
		olInteractionDragPan.setKineticMinVelocity(entityVo.getInteractionDragPanKineticMinVelocity());
		olInteractionList.add(olInteractionDragPan);

		OlInteraction olInteractionDragRotate = new OlInteraction();
		olInteractionDragRotate.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGROTATE);
		olInteractionDragRotate.setInteractionEnabled(entityVo.getInteractionDragRotate());
		olInteractionDragRotate.setCondition(entityVo.getInteractionDragRotateCondition());
		olInteractionDragRotate.setDuration(entityVo.getInteractionDragRotateDuration());
		olInteractionList.add(olInteractionDragRotate);

		OlInteraction olInteractionDragRotateAndZoom = new OlInteraction();
		olInteractionDragRotateAndZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGROTATEANDZOOM);
		olInteractionDragRotateAndZoom.setInteractionEnabled(entityVo.getInteractionDragRotateAndZoom());
		olInteractionDragRotateAndZoom.setCondition(entityVo.getInteractionDragRotateAndZoomCondition());
		olInteractionDragRotateAndZoom.setDuration(entityVo.getInteractionDragRotateAndZoomDuration());
		olInteractionList.add(olInteractionDragRotateAndZoom);

		OlInteraction olInteractionDragZoom = new OlInteraction();
		olInteractionDragZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_DRAGZOOM);
		olInteractionDragZoom.setInteractionEnabled(entityVo.getInteractionDragZoom());
		olInteractionDragZoom.setCondition(entityVo.getInteractionDragZoomCondition());
		olInteractionDragZoom.setDuration(entityVo.getInteractionDragZoomDuration());
		olInteractionDragZoom.setStyle(entityVo.getInteractionDragZoomStyle());
		olInteractionList.add(olInteractionDragZoom);

		OlInteraction olInteractionKeyboardPan = new OlInteraction();
		olInteractionKeyboardPan.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_KEYBOARDPAN);
		olInteractionKeyboardPan.setInteractionEnabled(entityVo.getInteractionKeyboardPan());
		olInteractionKeyboardPan.setDuration(entityVo.getInteractionKeyboardPanDuration());
		olInteractionKeyboardPan.setPixelDelta(entityVo.getInteractionKeyboardPanPixelDelta());
		olInteractionList.add(olInteractionKeyboardPan);

		OlInteraction olInteractionKeyboardZoom = new OlInteraction();
		olInteractionKeyboardZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_KEYBOARDZOOM);
		olInteractionKeyboardZoom.setInteractionEnabled(entityVo.getInteractionKeyboardZoom());
		olInteractionKeyboardZoom.setDuration(entityVo.getInteractionKeyboardZoomDuration());
		olInteractionKeyboardZoom.setDelta(entityVo.getInteractionKeyboardZoomDelta());
		olInteractionList.add(olInteractionKeyboardZoom);

		OlInteraction olInteractionMouseWheelZoom = new OlInteraction();
		olInteractionMouseWheelZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_MOUSEWHEELZOOM);
		olInteractionMouseWheelZoom.setInteractionEnabled(entityVo.getInteractionMouseWheelZoom());
		olInteractionMouseWheelZoom.setDuration(entityVo.getInteractionMouseWheelZoomDuration());
		olInteractionList.add(olInteractionMouseWheelZoom);

		OlInteraction olInteractionPinchRotate = new OlInteraction();
		olInteractionPinchRotate.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_PINCHROTATE);
		olInteractionPinchRotate.setInteractionEnabled(entityVo.getInteractionPinchRotate());
		olInteractionPinchRotate.setDuration(entityVo.getInteractionPinchRotateDuration());
		olInteractionPinchRotate.setThreshold(entityVo.getInteractionPinchRotateThreshold());
		olInteractionList.add(olInteractionPinchRotate);

		OlInteraction olInteractionPinchZoom = new OlInteraction();
		olInteractionPinchZoom.setInteractionClassName(ConstUtils.GIS_OL_INTERACTION_PINCHZOOM);
		olInteractionPinchZoom.setInteractionEnabled(entityVo.getInteractionPinchZoom());
		olInteractionPinchZoom.setDuration(entityVo.getInteractionPinchZoomDuration());
		olInteractionList.add(olInteractionPinchZoom);

		olInteractionService.save(entityVo.getMapId(), olInteractionList);

		response.setContentType("text/plain");
		response.getWriter().println(JsonUtils.ajaxResult(true));
	}
}
