package com.siims.auction.dao.goods;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siims.auction.dao.BaseDaoImpl;
import com.siims.auction.domain.Goods;
@Repository("IgoodsDao")
@SuppressWarnings("restriction")
@Transactional
public class GoodsDaoImpl extends BaseDaoImpl<Goods, String> implements IgoodsDao{

}
