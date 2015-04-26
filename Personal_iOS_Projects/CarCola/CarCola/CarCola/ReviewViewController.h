//
//  ReviewViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/13/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SharedNetworking.h"

@interface ReviewViewController : UIViewController

//@property (strong, nonatomic) NSMutableArray *data;
//@property NSMutableDictionary *links;



@property (weak, nonatomic) IBOutlet UILabel *GradeLabel;
@property (weak, nonatomic) IBOutlet UILabel *SummaryLabel;
@property (weak, nonatomic) IBOutlet UILabel *PerformanceLabel;
@property (weak, nonatomic) IBOutlet UILabel *ComfortLabel;
@property (weak, nonatomic) IBOutlet UILabel *InteriorLabel;
@property (weak, nonatomic) IBOutlet UILabel *ValueLabel;
@property (weak, nonatomic) IBOutlet UILabel *FunToDriveLabel;

- (IBAction)backButton:(id)sender;

@property NSString *grade;
@property NSString *summary;
@property NSString *performance;
@property NSString *comfort;
@property NSString *interior;
@property NSString *value;
@property NSString *funtodrive;

@end
