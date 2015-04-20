package org.kyll.myserver.base.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.kyll.myserver.base.common.BaseService;
import org.kyll.myserver.base.common.Vo;
import org.kyll.myserver.base.common.paginated.Dataset;
import org.kyll.myserver.base.sys.entity.Attachment;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User: Kyll
 * Date: 2014-11-07 13:19
 * 实体对象， 值对象常用工具类
 */
public final class POJOUtils {
	/**
	 * BeanUtils.copyProperties 方法的封装。
	 * 加入了对 null 值的处理， 不会将值为 null 的属性赋值为默认值
	 * @param dest 目标对象
	 * @param orig 源对象
	 */
	public static void copyProperties(Object dest, Object orig) {
		ConvertUtils.register(new ByteConverter(null), Byte.class);
		ConvertUtils.register(new CharacterConverter(null), Character.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new DateConverter(null), Date.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);

		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		ConvertUtils.deregister();
	}

	public static <T, P> T convert(Vo<P> vo, Class<T> clazz, BaseService<T, P> baseService) {
		T t = null;
		if (vo != null) {
			P p = vo.getId();
			if (p == null) {
				try {
					t = clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				t = baseService.get(p);
			}

			copyProperties(t, vo);
		}
		return t;
	}

	public static <E, V> V convert(E e, Class<V> clazz) {
		V v = null;
		if (e != null) {
			try {
				v = clazz.newInstance();
				copyProperties(v, e);
			} catch (InstantiationException | IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return v;
	}

	public static <E, V> List<V> convert(List<E> tList, Class<V> clazz) {
		List<V> vList = null;
		if (tList != null) {
			vList = new ArrayList<>();
			for (E e : tList) {
				vList.add(POJOUtils.convert(e, clazz));
			}
		}
		return vList;
	}

	public static <E, V> Dataset<V> convert(Dataset<E> tDataset, Class<V> clazz) {
		Dataset<V> vDataset = null;
		if (tDataset != null) {
			vDataset = new Dataset<>();
			vDataset.setPaginated(tDataset.getPaginated());
			vDataset.setDataList(POJOUtils.convert(tDataset.getDataList(), clazz));
		}
		return vDataset;
	}

	public static <E, V> V convert(E e, Class<V> clazz, VoHandler<E, V> voHandler) {
		V v = null;
		if (e != null) {
			try {
				v = clazz.newInstance();
				copyProperties(v, e);
				voHandler.handler(e, v);
			} catch (InstantiationException | IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return v;
	}

	public static <E, V> List<V> convert(List<E> tList, Class<V> clazz, VoHandler<E, V> voHandler) {
		List<V> vList = null;
		if (tList != null) {
			vList = new ArrayList<>();
			for (E e : tList) {
				vList.add(POJOUtils.convert(e, clazz, voHandler));
			}
		}
		return vList;
	}

	public static <E, V> Dataset<V> convert(Dataset<E> tDataset, Class<V> clazz, VoHandler<E, V> voHandler) {
		Dataset<V> vDataset = null;
		if (tDataset != null) {
			vDataset = new Dataset<>();
			vDataset.setPaginated(tDataset.getPaginated());
			vDataset.setDataList(POJOUtils.convert(tDataset.getDataList(), clazz, voHandler));
		}
		return vDataset;
	}

	public static List<CommonsMultipartFile> getCommonsMultipartFileList(Object object) {
		List<CommonsMultipartFile> commonsMultipartFileList = new ArrayList<>();

		Class clazz = object.getClass();
		Method[] methods = clazz.getMethods();
		try {
			for (Method method : methods) {
				Class returnType = method.getReturnType();
				if (CommonsMultipartFile.class == returnType) {
					Object returnValue = method.invoke(object);
					if (returnValue != null) {
						commonsMultipartFileList.add((CommonsMultipartFile) returnValue);
					}
				} else if (CommonsMultipartFile[].class == returnType) {
					Object returnValue = method.invoke(object);
					if (returnValue != null) {
						CommonsMultipartFile[] commonsMultipartFiles = (CommonsMultipartFile[]) returnValue;
						for (CommonsMultipartFile commonsMultipartFile : commonsMultipartFiles) {
							if (commonsMultipartFile.getSize() > 0) {
								commonsMultipartFileList.add(commonsMultipartFile);
							}
						}
					}
				}
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return commonsMultipartFileList;
	}

	public static Attachment convert(CommonsMultipartFile commonsMultipartFile) {
		Attachment attachment = new Attachment();
	//	attachment.setEntityName();
	//	attachment.setEntityId();
		attachment.setContentType(commonsMultipartFile.getContentType());
		attachment.setFileSize(commonsMultipartFile.getSize());

		String originalFilename = commonsMultipartFile.getOriginalFilename();
		attachment.setOriginalFilename(originalFilename);
		attachment.setRandomFilename(originalFilename.substring(0, originalFilename.lastIndexOf('.')) + "_" + UUID.randomUUID().toString().replace("-", ""));
		attachment.setExtensionName(originalFilename.substring(originalFilename.lastIndexOf('.') + 1));

		return attachment;
	}

	public interface VoHandler<T, V> {
		void handler(T t, V v);
	}

	private POJOUtils() {
	}
}
