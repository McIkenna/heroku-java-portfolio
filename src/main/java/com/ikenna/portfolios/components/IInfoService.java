package com.ikenna.portfolios.components;

import com.ikenna.portfolios.infos.Info;
import org.springframework.stereotype.Component;

@Component
public interface IInfoService {
    Info saveOrUpdateInfo(Info info);
}
