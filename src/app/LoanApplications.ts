export class LoanApplications {
    applicationId: number;
    applicantName: string;
    applicantPhone: number;
    applicantEmail: string;
    applicantProfession: string;
    applicantAddress: string;
    applicationDate: string;
    applicantPAN: string;
    monthlyIncome: number;
    noOfDependents: number;
    applicationStatus:string;
    loanPlanId: number;
    applicationReviewedOn: string;
    loanDocs:[];
}

export class DocumentTypes{
    constructor(
        public documentId: number,
    public documentType: string,
    ){}
}