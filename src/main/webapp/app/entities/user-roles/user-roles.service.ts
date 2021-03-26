import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserRoles } from 'app/shared/model/user-roles.model';

type EntityResponseType = HttpResponse<IUserRoles>;
type EntityArrayResponseType = HttpResponse<IUserRoles[]>;

@Injectable({ providedIn: 'root' })
export class UserRolesService {
  public resourceUrl = SERVER_API_URL + 'api/user-roles';

  constructor(protected http: HttpClient) {}

  create(userRoles: IUserRoles): Observable<EntityResponseType> {
    return this.http.post<IUserRoles>(this.resourceUrl, userRoles, { observe: 'response' });
  }

  update(userRoles: IUserRoles): Observable<EntityResponseType> {
    return this.http.put<IUserRoles>(this.resourceUrl, userRoles, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserRoles>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserRoles[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
